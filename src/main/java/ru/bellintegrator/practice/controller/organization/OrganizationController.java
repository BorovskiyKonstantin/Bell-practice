package ru.bellintegrator.practice.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.dto.ApiResponse;
import ru.bellintegrator.practice.dto.DataResponse;
import ru.bellintegrator.practice.dto.organization.OrganizationDtoGetListIn;
import ru.bellintegrator.practice.dto.organization.OrganizationDtoSaveIn;
import ru.bellintegrator.practice.dto.organization.OrganizationDtoUpdateIn;
import ru.bellintegrator.practice.service.organization.OrganizationService;

import javax.validation.Valid;


/**
 * Контроллер для запросов api/organization/*
 */
@RestController
@RequestMapping("api/organization/")
public class OrganizationController {
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    //POST api/organization/list
    @PostMapping("list")
    public ApiResponse list(@RequestBody @Valid OrganizationDtoGetListIn dto) {
        return new DataResponse(organizationService.findAll(dto));
    }

    //GET api/organization/{id}
    @GetMapping("{id}")
    public ApiResponse getById(@PathVariable("id") Integer id) {
        return new DataResponse(organizationService.findById(id));
    }

    //POST api/organization/update
    @PostMapping("update")
    public ApiResponse update(@RequestBody @Valid OrganizationDtoUpdateIn dto) {
        return new DataResponse(organizationService.update(dto));
    }

    //POST api/organization/save
    @PostMapping("save")
    public ApiResponse save(@RequestBody @Valid OrganizationDtoSaveIn dto) {
        return new DataResponse(organizationService.save(dto));
    }
}
