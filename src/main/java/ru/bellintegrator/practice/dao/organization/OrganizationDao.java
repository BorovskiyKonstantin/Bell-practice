package ru.bellintegrator.practice.dao.organization;

import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить список Organization по переданным параметрам
     *
     * @param name
     * @param inn
     * @param isActive
     * @return
     */
    List<Organization> findAll(String name, String inn, Boolean isActive);

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization findById(Integer id);

    /**
     * Сохранить организацию в БД
     *
     * @param org
     */
    void save(Organization org);

}
