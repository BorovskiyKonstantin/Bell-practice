package ru.bellintegrator.practice.service.user;

import ru.bellintegrator.practice.dto.user.*;

import java.util.List;

public interface UserService {
    List<UserDtoGetListOut> findAll(UserDtoGetListIn dto);

    UserDtoGetByIdOut findById(Integer id);

    UserDtoUpdateOut update(UserDtoUpdateIn dto);

    UserDtoSaveOut save(UserDtoSaveIn dto);
}
