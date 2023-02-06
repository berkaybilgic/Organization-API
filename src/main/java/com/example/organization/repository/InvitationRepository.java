package com.example.organization.repository;

import com.example.organization.model.Invitation;
import com.example.organization.model.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    @Modifying
    @Query("Update Invitation i SET i.status = ?1 WHERE  i.user.id =?2 and i.organization.registryNumber =?3")
    int updateInvitationStatus(InvitationStatus status, UUID userId, UUID organizationId);

    boolean existsByStatus(InvitationStatus status);

    Invitation findById(UUID id);

    @Query("SELECT i from Invitation i where i.user.id = ?1 and i.organization.registryNumber = ?2")
    Invitation findByUserIdAndOrganizationIdExists(UUID userId, UUID organizationId);

    @Query("SELECT i from Invitation i where i.status = ?1 and i.user.id =?2 and i.organization.registryNumber =?3")
    Invitation findByNonPending(InvitationStatus status, UUID userId, UUID organizationId);

    @Modifying
    @Query("DELETE from Invitation i where i.status = ?1 and i.user.id =?2 and i.organization.registryNumber =?3")
    int deleteInvitation(InvitationStatus status, UUID userId, UUID organizationId);


}
