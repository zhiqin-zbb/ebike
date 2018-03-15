package com.aaebike.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aaebike.common.constants.ErrorConstants;
import com.aaebike.entity.base.ResponseVo;

@Controller
@RequestMapping("/api")
public class FileApiController {
    private static final Resource PICTURES_DIR = new FileSystemResource("/data/img/");

    private static final String PATH_PREFIX = "/data/img/";

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "uploadPage";
    }

    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo upload(MultipartFile file) throws IOException {
        if (!isImage(file)) {
            return ResponseVo.valueOf(false, null, ErrorConstants.NOT_IMAGE_FILE_ERROR);
        }

        String filename = file.getOriginalFilename();
        File tempFile = File.createTempFile("file", getFileExtension(filename), PICTURES_DIR.getFile());
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return ResponseVo.valueOf(true, PATH_PREFIX + tempFile.getName(), null);
    }

    private static String getFileExtension(String name) {
        return name.substring(name.lastIndexOf("."));
    }

    private boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResponseVo handleIOException() {
        return ResponseVo.valueOf(false, null, ErrorConstants.FILE_IO_EXCEPTION);
    }

    @RequestMapping("/file/uploadError")
    @ResponseBody
    public ResponseVo onUploadError() {
        return ResponseVo.valueOf(false, null, ErrorConstants.FILE_SIZE_EXCEEDED_EXCEPTION);
    }
}
