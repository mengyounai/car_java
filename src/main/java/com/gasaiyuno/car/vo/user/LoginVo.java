package com.gasaiyuno.car.vo.user;


import com.gasaiyuno.car.vo.common.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginVo extends BaseVo {

    @NotNull(message = "userName:用户名不能为空")
    private String username;

    @NotNull(message = "password:密码不能为空")
    private String password;

}
