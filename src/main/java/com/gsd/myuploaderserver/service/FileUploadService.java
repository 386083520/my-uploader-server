package com.gsd.myuploaderserver.service;

import com.gsd.myuploaderserver.models.FileInfo;
import com.gsd.myuploaderserver.models.MultipartFileParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    ResponseEntity<String> uploadFile(MultipartFileParams file);
    ResponseEntity<String> mergeFile(FileInfo fileInfo);
}
