package com.gasaiyuno.car.service;

import com.UpYun;
import com.gasaiyuno.car.util.CodeCreateUtils;
import com.upyun.UpException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

public interface UpYunService {

    String upLoadImage(MultipartFile file) throws IOException, UpException;

}
