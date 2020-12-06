package ru.bellintegrator.practice.service.office;

import ru.bellintegrator.practice.dto.SuccessResponse;
import ru.bellintegrator.practice.dto.office.*;

import java.util.List;


public interface OfficeService {
    List<OfficeDtoListOut> findAll(OfficeDtoListIn dto);

    OfficeDtoGetByIdOut findById(Integer id);

    SuccessResponse update(OfficeDtoUpdateIn dto);

    SuccessResponse save(OfficeDtoSaveIn dto);
}
