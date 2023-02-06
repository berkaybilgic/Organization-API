package com.example.organization.controller;

import com.example.organization.model.Organization;
import com.example.organization.model.User;
import com.example.organization.model.dto.UserCreationDto;
import com.example.organization.model.dto.UserDeleteDto;
import com.example.organization.model.dto.UserUpdateStatusDto;
import com.example.organization.service.OrganizationService;
import com.example.organization.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;
    private final OrganizationService organizationService;

    @Autowired
    public UserController(UserService userService, OrganizationService organizationService) {
        this.userService = userService;
        this.organizationService = organizationService;
    }

    @PostMapping()
    @Operation(summary = "Create user")
    public ResponseEntity<User> createUser(@RequestBody UserCreationDto userCreationDTO) {
        User user = userService.createUser(userCreationDTO);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping()
    @Operation(summary = "Delete user")
    public void deleteUser(@RequestBody UserDeleteDto userDeleteDTO) {
        userService.deleteUser(userDeleteDTO);
    }

    @PutMapping("/status")
    @Operation(summary = "Update user status")
    public ResponseEntity<User> updateStatus(@RequestBody UserUpdateStatusDto userUpdateStatusDTO) {
        User user = userService.updateStatus(userUpdateStatusDTO);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/find-by-email")
    @Operation(summary = "Gets user by email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Get the organizations the user is associated with", operationId = "getByEmail",
            parameters = {@Parameter(in = ParameterIn.QUERY, name = "email", description = "User Email")})
    @GetMapping("/find-users-organization-by-email")
    public ResponseEntity<List<Organization>> getUserOrganizationByEmail(@RequestParam String email) {
        List<Organization> org = userService.getUserOrganizationByEmail(email);

        return ResponseEntity.status(HttpStatus.OK).body(org);
    }

    //@GetMapping("/user/normalized-name")
    @Operation(summary = "Gets users normalized name")
    @RequestMapping(value = "/find-users-by-normalized-name", params = "normalizedName", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserOrganizationByNormalizedName(@RequestParam String normalizedName) {
        List<User> users = userService.getUserOrganizationByNormalizedName(normalizedName);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
