package com.gsd.myuploaderserver.service.impl;

import com.gsd.myuploaderserver.models.FileInfo;
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
        String identifier = fileParams.getIdentifier();
        log.info("identifier---" + identifier);
        int chunkNumber = fileParams.getChunkNumber();
        File fileTemp = new File(uploadFilePath + "/" + identifier + "/" + chunkNumber);

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

    @Override
    public ResponseEntity<String> mergeFile(FileInfo fileInfo) {
        log.info("fileName:" + fileInfo.getName());
        log.info("UniqueIdentifier:" + fileInfo.getUniqueIdentifier());
        return ResponseEntity.ok("SUCCESS");
    }
}
