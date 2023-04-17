package com.gasaiyuno.car.service.impl;

import com.UpYun;
import com.gasaiyuno.car.config.UpYunConfig;
import com.gasaiyuno.car.service.UpYunService;
import com.gasaiyuno.car.util.CodeCreateUtils;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
public class UpYunServiceImpl implements UpYunService {

    @Autowired
    private UpYunConfig upYunConfig;

    @Override
    public String upLoadImage(MultipartFile file) throws IOException, UpException {
        UpYun upYun = new UpYun(upYunConfig.getBucketName(), upYunConfig.getUsername(), upYunConfig.getPassword());
        String format = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        String fileName = "/car/" + name + CodeCreateUtils.get4Code(4) + "." + format;
        boolean b1 = upYun.writeFile(fileName, file.getInputStream(), true, new HashMap<>());
        String firstPicture = "http://" + upYunConfig.getBucketName() + "." + upYunConfig.getHostName() + "/" + fileName;
        return firstPicture;
    }
}
