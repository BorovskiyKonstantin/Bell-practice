package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.dto.ApiResponse;
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
    OrganizationDtoUpdateOut update(OrganizationDtoUpdateIn dto);

    /**
     * Сохранить новую органиацию в БД
     *
     * @param dto
     * @return
     */
    OrganizationDtoSaveOut save(OrganizationDtoSaveIn dto);
}
