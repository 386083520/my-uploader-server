package com.gsd.myuploaderserver.service.impl;

import com.gsd.myuploaderserver.models.MultipartFileParams;
import com.gsd.myuploaderserver.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${upload.file.path}")
    private String uploadFilePath;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFileParams fileParams) {
        String fileName = fileParams.getFilename();
        log.info("fileName---" + fileName);
        String fileDir = fileName.split("\\.")[0];
        int chunkNumber = fileParams.getChunkNumber();
        File fileTemp = new File(uploadFilePath + "/" + fileDir + "/" + chunkNumber);

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
