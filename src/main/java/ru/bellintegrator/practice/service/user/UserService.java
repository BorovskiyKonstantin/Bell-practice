package ru.bellintegrator.practice.service.user;

import ru.bellintegrator.practice.dto.SuccessResponse;
import ru.bellintegrator.practice.dto.user.*;

import java.util.List;

public interface UserService {
    List<UserDtoGetListOut> findAll(UserDtoGetListIn dto);

    UserDtoGetByIdOut findById(Integer id);

    SuccessResponse update(UserDtoUpdateIn dto);

    SuccessResponse save(UserDtoSaveIn dto);
}
