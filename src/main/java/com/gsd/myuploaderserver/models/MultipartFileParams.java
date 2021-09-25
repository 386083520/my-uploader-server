package com.gsd.myuploaderserver.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MultipartFileParams {
    private int chunkNumber;
    private long chunkSize;
    private long currentChunkSize;
    private long totalSize;
    private String identifier;
    private String filename;
    private String relativePath;
    private int totalChunks;
    private MultipartFile file;
}
