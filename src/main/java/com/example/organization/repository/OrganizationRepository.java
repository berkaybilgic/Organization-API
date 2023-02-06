package com.example.organization.repository;

import com.example.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("SELECT o from Organization o where o.organizationName = ?1")
    List<Organization> findByOrganizationsName(String name);

    Organization findByOrganizationName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM users p JOIN organization_users t ON p.id = t.user_id JOIN organization pt ON t.organization_id = pt.registry_number WHERE p.email = ?1")
    List<Organization> getUserRelatedOrganization(String email);

    boolean existsByContactEmail(String email);

    Organization findByRegistryNumber(UUID uuid);

    Organization findByContactEmail(String email);

    void deleteByRegistryNumber(UUID id);

    boolean existsByRegistryNumber(UUID registryNumber);
}
