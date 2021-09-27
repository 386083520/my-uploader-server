package com.gsd.myuploaderserver.controller;

import com.gsd.myuploaderserver.models.FileInfo;
import com.gsd.myuploaderserver.models.MultipartFileParams;
import com.gsd.myuploaderserver.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/upload", produces = "application/json; charset=UTF-8")
public class UploadController {
    @Autowired
    FileUploadService fileUploadService;

    @PostMapping(value = "/uploadFile")
    public ResponseEntity<String> upload(MultipartFileParams file) {
        log.info("file");
        return fileUploadService.uploadFile(file);
    }

    @GetMapping(value = "/uploadFile")
    public ResponseEntity<Object> uploadCheck(MultipartFileParams file) {
        log.info("file");
        return fileUploadService.uploadCheck(file);
    }

    @PostMapping(value = "/mergeFile")
    public ResponseEntity<String> mergeFile(@RequestBody FileInfo file) {
        return fileUploadService.mergeFile(file);
    }
}
