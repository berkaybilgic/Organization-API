package com.example.organization.service;

import com.example.organization.model.Invitation;
import com.example.organization.model.InvitationStatus;
import com.example.organization.model.Organization;
import com.example.organization.model.User;
import com.example.organization.model.dto.InvitationCreationDto;
import com.example.organization.model.dto.InvitationStatusDto;
import com.example.organization.model.dto.InvitationUpdateDto;
import com.example.organization.repository.InvitationRepository;
import com.example.organization.repository.OrganizationRepository;
import com.example.organization.repository.UserRepository;
import com.example.organization.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    private final UserRepository userRepository;

    private final OrganizationRepository organizationRepository;

    @Autowired
    public InvitationService(InvitationRepository invitationRepository, UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    public Invitation invitationUpdate(InvitationUpdateDto invitationUpdateDTO) {

        boolean isValidEmail = CommonUtils.isValidEmail(invitationUpdateDTO.getUserEmail());

        if (!isValidEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address is not valid");
        }

        Invitation inv = this.invitationRepository.findById(invitationUpdateDTO.getId());

        if (inv == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No record found for this invitation id");
        }

        User user = userRepository.findByEmail(invitationUpdateDTO.getUserEmail());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Organization organization = organizationRepository.findByContactEmail(invitationUpdateDTO.getOrganizationEmail());

        if (organization == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }


        inv.setInvitationMessage(invitationUpdateDTO.getInvitationMessage());
        inv.setStatus(invitationUpdateDTO.getStatus());
        inv.setUser(user);
        inv.setUpdatedBy(invitationUpdateDTO.getUpdatedUser());
        inv.setOrganization(organization);

        invitationRepository.save(inv);

        return inv;

    }


    public void invitationStatusUpdate(InvitationStatusDto invitationStatus) {

        boolean isValidEmail = CommonUtils.isValidEmail(invitationStatus.getUserEmail());

        if (!isValidEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address is not valid");
        }

        User user = userRepository.findByEmail(invitationStatus.getUserEmail());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Organization organization = organizationRepository.findByContactEmail(invitationStatus.getOrganizationEmail());

        if (organization == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }

        Invitation pending = this.invitationRepository.findByNonPending(InvitationStatus.PENDING, user.getId(), organization.getRegistryNumber());

        if (pending == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You don't have a pending invitation");
        }

        int affectedRow = invitationRepository.updateInvitationStatus(invitationStatus.getStatus(), user.getId(), organization.getRegistryNumber());

        Set<Organization> organizations = new HashSet<>();
        organizations.add(organization);
        user.setOrganization(organizations);
        userRepository.save(user);

        if (affectedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Check the entered information. Failed to delete invitation");
        }

    }

    public Invitation createInvitation(InvitationCreationDto invitationDto) {

        boolean isValidEmail = CommonUtils.isValidEmail(invitationDto.getUserEmail());

        if (!isValidEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address is not valid");
        }

        Organization organization = organizationRepository.findByContactEmail(invitationDto.getOrganizationEmail());

        if (organization == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }

        User user = userRepository.findByEmail(invitationDto.getUserEmail());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Invitation invitation = this.invitationRepository.findByUserIdAndOrganizationIdExists(user.getId(), organization.getRegistryNumber());

        if (invitation != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User previously" + invitation.getStatus() + "this company's invitation");
        }

        Invitation inv = new Invitation(user, invitationDto.getMessage(), InvitationStatus.PENDING, organization, user.getId());
        invitationRepository.save(inv);

        return inv;

    }

    public void deleteInvitation(InvitationStatusDto invitationStatus) {

        boolean isValidEmail = CommonUtils.isValidEmail(invitationStatus.getUserEmail());

        if (!isValidEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address is not valid");
        }

        User user = userRepository.findByEmail(invitationStatus.getUserEmail());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Organization organization = organizationRepository.findByContactEmail(invitationStatus.getOrganizationEmail());

        if (organization == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization not found");
        }

        int affectedRow = invitationRepository.deleteInvitation(invitationStatus.getStatus(), user.getId(), organization.getRegistryNumber());

        if (affectedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Check the entered information. Failed to delete invitation");
        }

    }

    public List<Invitation> getInvitations() {
        return invitationRepository.findAll();
    }


}
