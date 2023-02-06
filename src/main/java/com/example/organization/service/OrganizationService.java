package com.example.organization.service;

import com.example.organization.model.Organization;
import com.example.organization.repository.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public OrganizationService(com.example.organization.repository.OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    public Organization findByRegistryNumber(UUID uuid) {
        return organizationRepository.findByRegistryNumber(uuid);
    }

    public List<Organization> findByOrganizationsName(String name) {
        return organizationRepository.findByOrganizationsName(name);
    }

    public Organization findByOrganizationName(String name) {
        return organizationRepository.findByOrganizationName(name);
    }

    public List<Organization> findAllOrganization() {
        return organizationRepository.findAll();
    }

    public boolean existsByEmail(String email) {
        return organizationRepository.existsByContactEmail(email);
    }

    public Organization convertDtoToOrganization(Object object) {
        return modelMapper.map(object, Organization.class);
    }

    public void userDelete(UUID id) {
        organizationRepository.deleteByRegistryNumber(id);
    }

    public boolean existsByRegistryNumber(UUID registryNumber) {
        return organizationRepository.existsByRegistryNumber(registryNumber);
    }


}
