package com.gasaiyuno.car.service;


import com.gasaiyuno.car.dto.BaseUserInfo;
import com.gasaiyuno.car.dto.user.UserDTO;
import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.vo.user.LoginVo;
import com.gasaiyuno.car.vo.user.UserCreateVo;

import java.util.List;

public interface UserService {

    User checkUser(String username, String password);

    User findUser(String userName);

    UserDTO login(LoginVo loginVo);

    User addUser(UserCreateVo createVo);

    UserDTO userInfo(BaseUserInfo baseUserInfo);

    int countUser();

    List<User> userList();

    User findById(Long id);

    int updateUser(User user);

}
