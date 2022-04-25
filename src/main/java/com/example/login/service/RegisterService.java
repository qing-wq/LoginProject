package com.example.login.service;

import com.example.login.mapper.UserMapper;
import com.example.login.pojo.User;
import com.example.login.pojo.vo.MessageModel;
import com.example.login.utils.MybatisUtil;
import com.example.login.utils.StringUtil;
import org.apache.ibatis.session.SqlSession;

public class RegisterService {
    private User user;     // 用户输入的User

    public RegisterService() {
    }

    public RegisterService(User user) {
        this.user = user;
    }

    public MessageModel UserRegister() {
        MessageModel messageModel = new MessageModel();
        messageModel.setData(user);
        if (StringUtil.isEmpty(user.getUsername())) {
            messageModel.setStateCode(400);
            messageModel.setMessage("用户名不能为空");
            return messageModel;
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            messageModel.setStateCode(400);
            messageModel.setMessage("密码不能为空");
            return messageModel;
        }
        if (StringUtil.isEmpty(user.getEmail())) {
            messageModel.setStateCode(400);
            messageModel.setMessage("邮箱不能为空");
        }
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User mapperUser = mapper.getUser(user.getUsername());   // 从数据库中取出的user
        if (ConUsername(mapperUser)) {
            messageModel.setStateCode(400);
            messageModel.setMessage("该用户名已存在");
            return messageModel;
        }
        messageModel.setStateCode(200);
        messageModel.setData(user);
        save(mapper,user);

        return messageModel;
    }

    // 将用户输入是我数据存储到数据库中
    private void save(UserMapper mapper,User mapperUser) {
        Integer n = mapper.UserInsert(mapperUser);
        System.out.println(n);
        if (n > 0) {
            System.out.println("注册用户成功");
        }
    }

    private boolean ConUsername(User mapperUser) {
        System.out.println(mapperUser);
        if (mapperUser == null) {
            return false;
        }
        System.out.println("已存在用户："+mapperUser);
        return true;
    }
}
