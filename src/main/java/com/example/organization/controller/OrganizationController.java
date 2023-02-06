package com.example.organization.controller;

import com.example.organization.model.Organization;
import com.example.organization.model.User;
import com.example.organization.model.dto.OrganizationCreateDto;
import com.example.organization.model.dto.OrganizationDeleteDto;
import com.example.organization.service.OrganizationService;
import com.example.organization.service.UserService;
import com.example.organization.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController()
@RequestMapping("/v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    private final UserService userService;

    @Autowired
    public OrganizationController(OrganizationService organizationService, UserService userService) {
        this.organizationService = organizationService;
        this.userService = userService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Organization>> getOrganizations() {
        List<Organization> allOrganizations = organizationService.findAllOrganization();
        return ResponseEntity.status(HttpStatus.OK).body(allOrganizations);
    }

    @PostMapping()
    public ResponseEntity<Void> createOrganization(@RequestBody OrganizationCreateDto organizationCreateDto) {
        boolean isValidUserEmail = CommonUtils.isValidEmail(organizationCreateDto.getUserMail());
        boolean isValidOrganizationEmail = CommonUtils.isValidEmail((organizationCreateDto.getContactEmail()));
        if (!isValidUserEmail && !isValidOrganizationEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid email");
        }
        boolean existByOrganization = organizationService.existsByRegistryNumber(organizationCreateDto.getRegistryNumber());
        boolean existByUser = userService.existsByEmail(organizationCreateDto.getUserMail());
        if (!existByUser) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (existByOrganization) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already exist organization");
        }
        organizationService.save(organizationService.convertDtoToOrganization(organizationCreateDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Organization> getOrganizationByRegistryNumber(@RequestParam UUID id) {
        Organization organization = organizationService.findByRegistryNumber(id);
        if (organization == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "organization not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<Organization>> getOrganizationByName(@RequestParam String organizatioName) {
        List<Organization> organization = organizationService.findByOrganizationsName(organizatioName);
        if (organization.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "organization not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organization);
    }

    @DeleteMapping()
    public ResponseEntity<Void> organizationDelete(@RequestBody OrganizationDeleteDto organizationDeleteDto) {
        boolean existByUser = userService.existsByEmail(organizationDeleteDto.getUserEmail());
        boolean existByOrganization = organizationService.existsByRegistryNumber(organizationDeleteDto.getRegistryNumber());
        if (existByOrganization && !existByUser) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization or User not found");
        }
        organizationService.userDelete(organizationDeleteDto.getRegistryNumber());
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Void> updateOrganization(@RequestBody OrganizationCreateDto organizationCreateDto) {
        boolean isValidUserEmail = CommonUtils.isValidEmail(organizationCreateDto.getUserMail());
        boolean isValidOrganizationEmail = CommonUtils.isValidEmail((organizationCreateDto.getContactEmail()));
        if (!isValidUserEmail && !isValidOrganizationEmail) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid email");
        }
        boolean existByOrganization = organizationService.existsByRegistryNumber(organizationCreateDto.getRegistryNumber());
        boolean existByUser = userService.existsByEmail(organizationCreateDto.getUserMail());
        if (!existByUser && existByOrganization) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization or User not found");
        }
        organizationService.save(organizationService.convertDtoToOrganization(organizationCreateDto));
        return ResponseEntity.noContent().build();
    }
}
