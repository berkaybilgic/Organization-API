package com.example.organization.service;

import com.example.organization.model.Organization;
import com.example.organization.model.User;
import com.example.organization.model.UserStatus;
import com.example.organization.model.dto.UserCreationDto;
import com.example.organization.model.dto.UserDeleteDto;
import com.example.organization.model.dto.UserUpdateStatusDto;
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
public class UserService {

    private final UserRepository userRepository;

    private final OrganizationRepository organizationRepository;

    @Autowired
    public UserService(UserRepository userRepository, OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    public User createUser(UserCreationDto userCreationDTO) {

        User creator = userRepository.findByEmail(userCreationDTO.getCreatorEmail());

        if (creator == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Creator user not found");
        }

        boolean isExist = userRepository.existsByEmail(userCreationDTO.getEmail());

        if (isExist) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email address has already been registered");
        }

        boolean isValidEmail = CommonUtils.isValidEmail(userCreationDTO.getEmail());

        if (!isValidEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email address is not valid");
        }

        Set<Organization> organizations = new HashSet<>();
        organizations.add(null);
        String normalizedName = CommonUtils.normalizedString(userCreationDTO.getFullName());
        User user = new User(userCreationDTO.getFullName(), userCreationDTO.getEmail(), normalizedName, UserStatus.INVITED, creator.getId());
        user.setOrganization(organizations);
        userRepository.save(user);

        return user;

    }

    public void deleteUser(UserDeleteDto userDeleteDTO) {

        int affectedRow = userRepository.deleteByEmail(userDeleteDTO.getEmail());

        if (affectedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The user to be deleted was not found.");
        }

    }

    public User updateStatus(UserUpdateStatusDto userUpdateStatusDTO) {

        User user = userRepository.findByEmail(userUpdateStatusDTO.getEmail());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        user.setStatus(userUpdateStatusDTO.getStatus());
        userRepository.save(user);

        return user;

    }

    public User getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return user;
    }

    public List<Organization> getUserOrganizationByEmail(String email) {

        List<Organization> organizations = organizationRepository.getUserRelatedOrganization(email);

        if (organizations == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return organizations;
    }

    public List<User> getUserOrganizationByNormalizedName(String normalizedName) {

        List<User> users = userRepository.findByNormalizedName(normalizedName);

        if (users == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return users;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
