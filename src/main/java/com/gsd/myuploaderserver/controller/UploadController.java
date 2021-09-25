package com.gsd.myuploaderserver.controller;

import com.gsd.myuploaderserver.models.MultipartFileParams;
import com.gsd.myuploaderserver.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/upload", produces = "application/json; charset=UTF-8")
public class UploadController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping(value = "/uploadFile")
    public ResponseEntity<String> upload(MultipartFileParams file) {
        log.info("file", "MultipartFileParams");
        return fileUploadService.uploadFile(file);
    }
}
