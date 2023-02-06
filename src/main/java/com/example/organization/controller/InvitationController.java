package com.example.organization.controller;

import com.example.organization.model.Invitation;
import com.example.organization.model.dto.InvitationCreationDto;
import com.example.organization.model.dto.InvitationStatusDto;
import com.example.organization.model.dto.InvitationUpdateDto;
import com.example.organization.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/invitation")
public class InvitationController {

    private final InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping()
    public ResponseEntity<Invitation> createInvitation(@RequestBody InvitationCreationDto invitationDTO) {
        Invitation invitation = invitationService.createInvitation(invitationDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
    }

    @PutMapping("/status")
    public void updateInvitationStatus(@RequestBody InvitationStatusDto invitationDTO) {
        invitationService.invitationStatusUpdate(invitationDTO);
    }

    @PutMapping()
    public ResponseEntity<Invitation> updateInvitation(@RequestBody InvitationUpdateDto updateDTO) {
        Invitation invitation = invitationService.invitationUpdate(updateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(invitation);

    }

    @DeleteMapping("")
    public ResponseEntity.BodyBuilder deleteInvitation(@RequestBody InvitationStatusDto invitationDTO) {
        invitationService.deleteInvitation(invitationDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<List<Invitation>> getInvitations() {
        List<Invitation> invitations = invitationService.getInvitations();
        return ResponseEntity.status(HttpStatus.OK).body(invitations);
    }
}