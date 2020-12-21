package ru.bellintegrator.practice.service.organization;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.dto.SuccessResponse;
import ru.bellintegrator.practice.dto.organization.*;
import ru.bellintegrator.practice.model.Organization;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    private MapperFacade mapper;
    {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Organization.class, OrganizationDtoGetByIdOut.class);
        mapperFactory.classMap(Organization.class, OrganizationDtoGetListOut.class);
        mapperFactory.classMap(OrganizationDtoUpdateIn.class, Organization.class);
        mapperFactory.classMap(OrganizationDtoSaveIn.class, Organization.class);
        mapper = mapperFactory.getMapperFacade();
    }

    private final OrganizationDao organizationDao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    @Override
    public List<OrganizationDtoGetListOut> findAll(OrganizationDtoGetListIn dto) {
        List<Organization> organizations = organizationDao.findAll(dto.getName(), dto.getInn(), dto.getIsActive());
        List<OrganizationDtoGetListOut> dtoList = organizations.stream()
                .map(organization -> mapper.map(organization, OrganizationDtoGetListOut.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public OrganizationDtoGetByIdOut findById(Integer id) {
        Organization org = organizationDao.findById(id);
        OrganizationDtoGetByIdOut dto = mapper.map(org, OrganizationDtoGetByIdOut.class);
        return dto;
    }

    @Override
    public SuccessResponse update(OrganizationDtoUpdateIn dto) {
        Organization persistedOrg = organizationDao.findById(dto.getId());
        persistedOrg.setName(dto.getName());
        persistedOrg.setFullName(dto.getFullName());
        persistedOrg.setInn(dto.getInn());
        persistedOrg.setKpp(dto.getKpp());
        persistedOrg.setAddress(dto.getAddress());
        if (dto.getPhone() != null) {
            persistedOrg.setPhone(dto.getPhone());
        }
        if (dto.getIsActive() != null) {
            persistedOrg.setIsActive(dto.getIsActive());
        }
        return new SuccessResponse();
    }

    @Override
    public SuccessResponse save(OrganizationDtoSaveIn dto) {
        Organization org = mapper.map(dto, Organization.class);
        organizationDao.save(org);
        return new SuccessResponse();
    }
}
