package ru.bellintegrator.practice.controller.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dto.ApiResponse;
import ru.bellintegrator.practice.dto.DataResponse;
import ru.bellintegrator.practice.service.country.CountryService;

@RestController
@RequestMapping("api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    //POST api/countries
    @PostMapping
    public ApiResponse get(){
        return new DataResponse(countryService.list());
    }
}
