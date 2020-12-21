package ru.bellintegrator.practice.controller.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dto.ApiResponse;
import ru.bellintegrator.practice.dto.DataResponse;
import ru.bellintegrator.practice.service.document.DocumentService;

@RestController
@RequestMapping("api/docs")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    //POST api/docs
    @PostMapping
    public ApiResponse docsList() {
        return new DataResponse(documentService.list());
    }
}
