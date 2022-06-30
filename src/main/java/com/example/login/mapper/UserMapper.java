package com.example.login.mapper;

import com.example.login.pojo.User;

public interface UserMapper {
    User getUser(String uname);
    Integer UserInsert(User user);
}
