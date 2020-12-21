package ru.bellintegrator.practice.dao.user;

import ru.bellintegrator.practice.model.User;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {
    /**
     * Получить все объекты User
     *
     * @return
     * @param officeId
     * @param firstName
     * @param lastName
     * @param middleName
     * @param position
     * @param docCode
     * @param citizenshipCode
     */
    List<User> findAll(Integer officeId, String firstName, String lastName, String middleName, String position, String docCode, String citizenshipCode);

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Integer id);


    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);
}
