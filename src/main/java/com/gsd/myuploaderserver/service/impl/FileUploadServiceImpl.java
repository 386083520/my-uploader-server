package com.gsd.myuploaderserver.service.impl;

import com.gsd.myuploaderserver.models.FileInfo;
import com.gsd.myuploaderserver.models.MultipartFileParams;
import com.gsd.myuploaderserver.service.FileUploadService;
import com.gsd.myuploaderserver.utils.CacheUtils;
import com.gsd.myuploaderserver.utils.MergeFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${upload.file.path}")
    private String uploadFilePath;

    @Resource
    private MergeFileUtil mergeFileUtil;

    @Override
    public ResponseEntity<String> uploadFile(MultipartFileParams fileParams) {
        String identifier = fileParams.getIdentifier();
        log.info("identifier---" + identifier);
        int chunkNumber = fileParams.getChunkNumber();
        File fileTemp = new File(uploadFilePath + "/" + identifier + "/temp/" + chunkNumber);

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
        CacheUtils.setValues("chunks_"+ identifier, fileParams.getTotalChunks() + "");
        return ResponseEntity.ok("SUCCESS");
    }

    @Override
    public ResponseEntity<String> mergeFile(FileInfo fileInfo) {
        log.info("fileName:" + fileInfo.getName());
        log.info("UniqueIdentifier:" + fileInfo.getUniqueIdentifier());
        String identifier = fileInfo.getUniqueIdentifier();
        int chunks = Integer.parseInt(CacheUtils.getMap().get("chunks_"+ identifier));
        String result = mergeFileUtil.mergeFile(uploadFilePath + "/" + identifier, fileInfo.getName(), chunks);
        return ResponseEntity.ok(result);
    }
}
