package com.example.springbootmall.serveice.impl;

import com.example.springbootmall.dao.UserDao;
import com.example.springbootmall.dto.UserLoginRequest;
import com.example.springbootmall.dto.UserRegisterRequest;
import com.example.springbootmall.model.User;
import com.example.springbootmall.serveice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊的emial
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null) {
            log.warn("此email {} 已被註冊使用過", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        //創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查User是否存在
        if(user == null) {
            log.warn("該email {} 尚未註冊過", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //檢查密碼是否相同
        if(user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            log.warn("該email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
