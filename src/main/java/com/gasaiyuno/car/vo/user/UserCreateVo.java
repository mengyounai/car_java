package com.gasaiyuno.car.vo.user;


import com.gasaiyuno.car.vo.common.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCreateVo extends BaseVo {

    @NotNull(message = "username:账户名称不能为空")
    private String username;

    private String nickName;

    @NotNull(message = "password:登录密码不能为空")
    private String password;

    private String phone;

    private Integer status;


}
