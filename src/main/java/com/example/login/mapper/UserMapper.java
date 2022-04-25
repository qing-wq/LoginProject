package com.example.login.mapper;

import com.example.login.pojo.User;

public interface UserMapper {
    public User getUser(String uname);
    public Integer UserInsert(User user);
}
