package ru.bellintegrator.practice.dao.office;

import ru.bellintegrator.practice.model.Office;

import java.util.Optional;

public interface OfficeDao {
    Office loadById(Integer id);
}
