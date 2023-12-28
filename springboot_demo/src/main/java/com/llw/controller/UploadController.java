package com.llw.controller;

import com.aliyun.oss.AliOSSUtils;
import com.llw.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {

//    @Autowired
//    private AliOSSUtils aliOSSUtils;

    @Autowired // 此处Autowire的AliOSSUtils并非此模块下定义的，而是通过自定义的起步依赖aliyun-oss-spring-boot-starter注入
    private AliOSSUtils aliOSSUtils;

    //本地存储文件
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传:{},{},{}", name, age, image);
//
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构造唯一的文件名-- uuid（通用唯一识别码）
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新文件名:{}", newFileName);
//
//        //将文件存储在服务器的磁盘目录中
//        image.transferTo(new File("D:\\Code\\IdeaProjects\\springboot_demo\\images\\" + newFileName));
//
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}", image.getOriginalFilename());

        //调用阿里云OSS工具类
        String url = aliOSSUtils.upload(image);

        log.info("文件上传完成, 文件访问的url: {}", url);

        return Result.success(url);
    }
}
