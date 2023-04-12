package com.gasaiyuno.car.dto;

import lombok.Data;

@Data
public class BaseUserInfo {

    protected Integer userId;

    protected String userName;

    protected Integer userType;

    protected Integer isAdmin;
}
