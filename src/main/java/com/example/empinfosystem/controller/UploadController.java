package com.example.empinfosystem.controller;


import com.example.empinfosystem.pojo.Result;
import com.example.empinfosystem.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("上传文件参数{}, {}, {}", username, age, image);
//        String originalFileName = image.getOriginalFilename();
//        int index = originalFileName.lastIndexOf(".");
//        String extName = originalFileName.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extName;
//        image.transferTo(new File("D:\\Work\\" + newFileName));
//        return Result.success();
//    }

    @Autowired
    AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，", image.getOriginalFilename());
        //调用阿里云OSS
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，访问的url:{}", url);
        return Result.success(url);
    }
}
