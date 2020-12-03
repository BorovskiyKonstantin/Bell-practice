package ru.bellintegrator.practice.service.office;

import ru.bellintegrator.practice.dto.office.*;

import java.util.List;


public interface OfficeService {
    List<OfficeDtoListOut> findAll(OfficeDtoListIn dto);

    OfficeDtoGetByIdOut findById(Integer id);

    OfficeDtoUpdateOut update(OfficeDtoUpdateIn dto);

    OfficeDtoSaveOut save(OfficeDtoSaveIn dto);
}
