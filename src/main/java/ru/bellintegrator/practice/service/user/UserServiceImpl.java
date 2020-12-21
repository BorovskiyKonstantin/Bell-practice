package ru.bellintegrator.practice.service.user;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.user.UserDao;
import ru.bellintegrator.practice.dto.SuccessResponse;
import ru.bellintegrator.practice.dto.user.*;
import ru.bellintegrator.practice.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private MapperFacade mapper;
    {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserDtoGetByIdOut.class)
                .field("lastName", "secondName")
                .field("document.docType.name", "docName")
                .field("document.number", "docNumber")
                .field("document.date", "docDate")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .byDefault()
                .register();
        mapperFactory.classMap(User.class, UserDtoGetListOut.class)
                .field("lastName", "secondName")
                .byDefault()
                .register();
        mapperFactory.classMap(UserDtoSaveIn.class, User.class)
                .field("secondName", "lastName")
                .byDefault()
                .register();
        mapperFactory.classMap(UserDtoUpdateIn.class, User.class);
        mapper = mapperFactory.getMapperFacade();
    }

    private final UserDao userDao;
    private final OfficeDao officeDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
    }

    @Override
    public List<UserDtoGetListOut> findAll(UserDtoGetListIn dto) {
        List<User> all = userDao.findAll(dto.getOfficeId(), dto.getFirstName(), dto.getLastName(), dto.getMiddleName(),
                dto.getPosition(), dto.getDocCode(), dto.getCitizenshipCode());
        List<UserDtoGetListOut> dtoList = all.stream()
                .map(user -> mapper.map(user, UserDtoGetListOut.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public UserDtoGetByIdOut findById(Integer id) {
        User user = userDao.loadById(id);
        return mapper.map(user, UserDtoGetByIdOut.class);
    }

    @Override
    public SuccessResponse update(UserDtoUpdateIn dto) {
        User user = userDao.loadById(dto.getId());
        //сохранить офис
        user.setOffice(officeDao.loadById(dto.getOfficeId()));
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getSecondName());
        user.setPosition(dto.getPosition());
        user.setPhone(dto.getPhone());
        //найти док по id
        user.getDocument().getDocType().setName(dto.getDocName());
        user.getDocument().setNumber(dto.getDocNumber());
        user.getDocument().setDate(dto.getDocDate());
        user.getCountry().setCode(dto.getCitizenshipCode());
        userDao.save(user);
        
        return new SuccessResponse();
    }

    @Override
    public SuccessResponse save(UserDtoSaveIn dto) {
        User user = mapper.map(dto, User.class);
        //сохранить офис
        userDao.save(user);
        return new SuccessResponse();
    }
}
