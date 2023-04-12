package com.gasaiyuno.car.controller;

import com.alibaba.fastjson.JSON;
import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dto.BaseUserInfo;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class BaseController {

    @Resource
    HttpServletRequest request;


    protected BaseUserInfo getBaseInfo() {
        BaseUserInfo userInfo;
        try {
            String accessToken = request.getHeader("token");
            Claims claims = new JwtUtil().decode(accessToken);
            userInfo = JSON.parseObject(JSON.toJSONString(claims), BaseUserInfo.class);
        } catch (Exception ignored) {
            throw new ServiceException(ReturnCode.token_expired_code, "token失效请重新登录");
        }
        return userInfo;
    }



}
