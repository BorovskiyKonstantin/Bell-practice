package ru.bellintegrator.practice.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.dto.ApiResponse;
import ru.bellintegrator.practice.dto.DataResponse;
import ru.bellintegrator.practice.dto.user.UserDtoGetListIn;
import ru.bellintegrator.practice.dto.user.UserDtoSaveIn;
import ru.bellintegrator.practice.dto.user.UserDtoUpdateIn;
import ru.bellintegrator.practice.service.user.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user/")
public class UserController {
    @Autowired
    private UserService userService;

    //POST api/user/list
    @PostMapping("list")
    public ApiResponse list (@RequestBody @Valid UserDtoGetListIn dto){
        return new DataResponse(userService.findAll(dto));
    }

    //GET api/user/{id}
    @GetMapping("{id}")
    public ApiResponse getById(@PathVariable("id") Integer id){
        return new DataResponse(userService.findById(id));
    }

    //POST api/user/update
    @PostMapping("update")
    public ApiResponse update(@RequestBody @Valid UserDtoUpdateIn dto){
        return new DataResponse(userService.update(dto));
    }

    //POST api/user/save
    @PostMapping("save")
    public ApiResponse save(@RequestBody @Valid UserDtoSaveIn dto){
        return new DataResponse(userService.save(dto));
    }
}
