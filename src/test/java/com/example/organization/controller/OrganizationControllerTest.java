package com.example.organization.controller;

import com.example.organization.model.Organization;
import com.example.organization.model.User;
import com.example.organization.model.dto.OrganizationCreateDto;
import com.example.organization.model.dto.OrganizationDeleteDto;
import com.example.organization.service.OrganizationService;
import com.example.organization.service.UserService;
import com.example.organization.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class OrganizationControllerTest {
    private final OrganizationService organizationService = mock((OrganizationService.class));
    private final UserService userService = mock((UserService.class));
    private final OrganizationController organizationController = new OrganizationController(organizationService, userService);

    @Test
    void createOrganization() {
        OrganizationCreateDto organizationCreateDto = new OrganizationCreateDto();
        organizationCreateDto.setUserMail("user@example.com");
        organizationCreateDto.setContactEmail("organization@example.com");
        organizationCreateDto.setRegistryNumber(UUID.randomUUID());

        Mockito.when(userService.existsByEmail(organizationCreateDto.getUserMail())).thenReturn(true);
        Mockito.when(organizationService.existsByRegistryNumber(organizationCreateDto.getRegistryNumber())).thenReturn(false);

        ResponseEntity<Void> response = organizationController.createOrganization(organizationCreateDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getOrganizationByRegistryNumber() {
        UUID id = UUID.randomUUID();
        Organization organization = new Organization();
        organization.setRegistryNumber(id);
        organization.setRegistryNumber(id);
        Mockito.when(organizationService.findByRegistryNumber(id)).thenReturn(organization);

        ResponseEntity<Organization> response = organizationController.getOrganizationByRegistryNumber(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(organization, response.getBody());
    }

    @Test
    public void testGetOrganizationByName() {
        String organizationName = "Test Organization";
        User user = new User();
        Set<User> users = new HashSet<>();
        users.add(user);
        Integer integer = 5;
        Integer integer2 = 6;
        Date date1 = new Date();
        Date date2 = new Date();
        List<Organization> expectedOrganization = Arrays.asList(new Organization(UUID.randomUUID(), organizationName, "X", "X", integer, "x", integer2, users, date1, date2, UUID.randomUUID(), UUID.randomUUID()));

        when(organizationService.findByOrganizationsName(organizationName)).thenReturn(expectedOrganization);

        ResponseEntity<List<Organization>> response = organizationController.getOrganizationByName(organizationName);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrganization, response.getBody());
    }


    @Test
    void organizationDelete() {
            OrganizationDeleteDto organizationDeleteDto = new OrganizationDeleteDto();
            organizationDeleteDto.setUserEmail("user@email.com");
            organizationDeleteDto.setRegistryNumber(UUID.randomUUID());

            when(userService.existsByEmail(organizationDeleteDto.getUserEmail())).thenReturn(false);
            when(organizationService.existsByRegistryNumber(organizationDeleteDto.getRegistryNumber())).thenReturn(true);

            try {
                organizationController.organizationDelete(organizationDeleteDto);
                fail();
            } catch (ResponseStatusException e) {
                assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
                assertEquals("Organization or User not found", e.getReason());
            }

            verify(userService).existsByEmail(organizationDeleteDto.getUserEmail());
            verify(organizationService).existsByRegistryNumber(organizationDeleteDto.getRegistryNumber());
            verify(organizationService, never()).userDelete(organizationDeleteDto.getRegistryNumber());
        }


    @Test
    public void testUpdateOrganization() {
        OrganizationCreateDto organizationCreateDto = new OrganizationCreateDto();
        organizationCreateDto.setUserMail("user@example.com");
        organizationCreateDto.setContactEmail("org@example.com");
        organizationCreateDto.setRegistryNumber(UUID.randomUUID());

        boolean isValidUserEmail = CommonUtils.isValidEmail(organizationCreateDto.getUserMail());
        assertTrue(isValidUserEmail);
        boolean isValidOrganizationEmail = CommonUtils.isValidEmail(organizationCreateDto.getContactEmail());
        assertTrue(isValidOrganizationEmail);
        when(organizationService.existsByRegistryNumber(organizationCreateDto.getRegistryNumber())).thenReturn(true);
        when(userService.existsByEmail(organizationCreateDto.getUserMail())).thenReturn(true);

        assertEquals(HttpStatus.NO_CONTENT, organizationController.updateOrganization(organizationCreateDto).getStatusCode());
    }
}
