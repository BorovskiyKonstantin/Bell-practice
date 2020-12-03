package ru.bellintegrator.practice.service.country;

import ru.bellintegrator.practice.dto.country.CountryDtoOut;

import java.util.List;

public interface CountryService {
    List<CountryDtoOut> list();
}
