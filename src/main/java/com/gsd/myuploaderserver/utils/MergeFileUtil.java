package com.gsd.myuploaderserver.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

@Component
public class MergeFileUtil {
    public String mergeFile(String srcFile, String fileName, int chunks) {
        FileOutputStream fileOutputStream = null;
        SequenceInputStream sis = null;
        try {
            ArrayList<FileInputStream> fileInputStreamList = new ArrayList<>();
            for (int i = 1; i <= chunks; i++) {
                fileInputStreamList.add(new FileInputStream(new File(srcFile + "/temp/", i+"")));
            }
            Enumeration<FileInputStream> en = Collections.enumeration(fileInputStreamList);
            sis = new SequenceInputStream(en);
            File file = new File(srcFile + "/" + fileName);
            fileOutputStream = new FileOutputStream(file);
            int len = 0;
            byte buffer[] = new byte[1024];
            while ((len = sis.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(sis != null) {
                try {
                    sis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "SUCCESS";
    }
}
