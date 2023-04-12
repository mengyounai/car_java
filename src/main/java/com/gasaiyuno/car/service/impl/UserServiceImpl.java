package com.gasaiyuno.car.service.impl;

import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dao.UserRepository;
import com.gasaiyuno.car.dto.BaseUserInfo;
import com.gasaiyuno.car.dto.user.UserDTO;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.service.UserService;
import com.gasaiyuno.car.util.*;
import com.gasaiyuno.car.vo.user.LoginVo;
import com.gasaiyuno.car.vo.user.UserCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User findUser(String userName) {
        User user = userRepository.findByUsername(userName);
        return user;
    }

    @Override
    public UserDTO login(LoginVo loginVo) {
        String userName = loginVo.getUsername();
        String convert = PasswordUtil.passWordConvert(loginVo.getPassword());
//        if (PasswordUtil.isNotStringPwd(convert)) {
//            int num = addLoginNum(userName);
//            throw new ServiceException(ReturnCode.check_error, "用户不存在或密码错误，还可尝试：" + num + " 次");
//        }
        User user = userRepository.findByUsername(loginVo.getUsername());
        if (EmptyUtil.isEmpty(user)) {
            throw new ServiceException(ReturnCode.check_error, "用户不存在或密码错误");
        }
        String passWord = PasswordUtil.passWordSha1(userName, convert);
        if (!passWord.equals(user.getPassword())) {
            throw new ServiceException(ReturnCode.check_error, "用户不存在或密码错误" );
        }
        UserDTO userDTO = new UserDTO();
//        if (!EnumStatus.VAILD.getCode().equals(user.getStatus())) {
//            throw new ServiceException(ReturnCode.check_error, "用户已被禁用");
//        }

        userDTO.setUser(user);
        JwtUtil jwtUtil = new JwtUtil();
        // 用户信息
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("userId", user.getId());
        userInfoMap.put("userName", user.getUsername());
        userInfoMap.put("isAdmin", user.getType());
        userInfoMap.put("userType", user.getType());
        // jwt 有效时间为 1 小时
        String jwtToken = jwtUtil.encode(userInfoMap);
        userDTO.setToken(jwtToken);
        return userDTO;
    }

    @Override
    public User addUser(UserCreateVo createVo) {
        //判断当前用户是否创建过 账户
        String convert = PasswordUtil.passWordConvert(createVo.getPassword());
        User model = new User();
        createVo.copyIgnoreNullPropertiesTo(model);
        if (EmptyUtil.isEmpty(createVo.getNickName())) {
            model.setNickname(createVo.getUsername());
        }

        model.setType(EnumStatus.INVAILD.getCode());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setPassword(PasswordUtil.passWordSha1(model.getUsername(), convert));
        userRepository.save(model);
//        model.insert();
        return model;
    }

    @Override
    public UserDTO userInfo(BaseUserInfo baseUserInfo) {
        User user = userRepository.findByUsername(baseUserInfo.getUserName());
        UserDTO userDTO = new UserDTO();
        userDTO.setUser(user);
        return userDTO;
    }


}
