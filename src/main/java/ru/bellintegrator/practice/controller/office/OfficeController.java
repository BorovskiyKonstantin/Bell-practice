package ru.bellintegrator.practice.controller.office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.dto.ApiResponse;
import ru.bellintegrator.practice.dto.DataResponse;
import ru.bellintegrator.practice.dto.office.OfficeDtoListIn;
import ru.bellintegrator.practice.dto.office.OfficeDtoSaveIn;
import ru.bellintegrator.practice.dto.office.OfficeDtoUpdateIn;
import ru.bellintegrator.practice.service.office.OfficeService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/office/")
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    //POST api/office/list
    @PostMapping("list")
    public ApiResponse list(@RequestBody @Valid OfficeDtoListIn dto) {
        return new DataResponse(officeService.findAll(dto));
    }

    //GET api/office/{id}
    @GetMapping("{id}")
    public ApiResponse getById(@PathVariable("id") Integer id) {
        return new DataResponse(officeService.findById(id));
    }

    //POST api/office/update
    @PostMapping("update")
    public ApiResponse update(@RequestBody @Valid OfficeDtoUpdateIn dto) {
        return new DataResponse(officeService.update(dto));
    }

    //POST api/office/save
    @PostMapping("save")
    public ApiResponse save(@RequestBody @Valid OfficeDtoSaveIn dto) {
        return new DataResponse(officeService.save(dto));
    }
}
