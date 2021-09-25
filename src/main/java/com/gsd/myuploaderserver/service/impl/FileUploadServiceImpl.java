package com.gsd.myuploaderserver.service.impl;

import com.gsd.myuploaderserver.models.MultipartFileParams;
import com.gsd.myuploaderserver.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${upload.file.path}")
    private String uploadFilePath;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFileParams fileParams) {
        File fileTemp = new File(uploadFilePath + "/" + fileParams.getFilename());
        File fileParent = fileTemp.getParentFile();
        if(!fileParent.exists()) {
            fileParent.mkdirs();
        }
        try {
            MultipartFile file = fileParams.getFile();
            file.transferTo(fileTemp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("SUCCESS");
    }
}
