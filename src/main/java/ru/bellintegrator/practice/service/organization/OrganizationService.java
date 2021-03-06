package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.dto.SuccessResponse;
import ru.bellintegrator.practice.dto.organization.*;

import java.util.List;

public interface OrganizationService {
    /**
     * Найти организации по переданным параметрам
     *
     * @param dto
     * @return
     */
    List<OrganizationDtoGetListOut> findAll(OrganizationDtoGetListIn dto);

    /**
     * Найти организацию по переданному id
     *
     * @param id
     * @return
     */
    OrganizationDtoGetByIdOut findById(Integer id);

    /**
     * Обновить существующую в БД организацию
     *
     * @param dto
     * @return
     */
    SuccessResponse update(OrganizationDtoUpdateIn dto);

    /**
     * Сохранить новую органиацию в БД
     *
     * @param dto
     * @return
     */
    SuccessResponse save(OrganizationDtoSaveIn dto);
}
