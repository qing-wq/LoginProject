package com.example.login.service;
import com.example.login.mapper.UserMapper;
import com.example.login.pojo.User;
import com.example.login.pojo.vo.MessageModel;
import com.example.login.utils.MybatisUtil;
import com.example.login.utils.StringUtil;
import org.apache.ibatis.session.SqlSession;

// 调用Dao层去数据库中查找数据
// 逻辑：获取用户名去数据库中查找，如果有这个账号，返回账号的密码，与前台密码做比较；
// 如果查不到账号，返回账号错误；如果密码不相等，返回密码错误
// 用一个JavaBean存储用户输入的数据

public class UserServiceImp{
    private User user;
    private MessageModel mm;

    public UserServiceImp() {
    }

    public UserServiceImp(User user) {
        this.user = user;
    }



    public MessageModel Service() {
        mm = new MessageModel();
        mm.setData(user);
        if (StringUtil.isEmpty(user.getUsername())) {
            mm.setStateCode(400);
            mm.setMessage("用户名输入为空");
            return mm;
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            mm.setStateCode(400);
            mm.setMessage("密码不能为空");
            return mm;
        }
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User mapperUser = mapper.getUser(user.getUsername());   // 从数据库中获取的用户对象
        if (mapperUser == null){
            mm.setStateCode(400);
            mm.setMessage("账号不存在，请重新输入...");
            return mm;
        }
        if (mapperUser.getPassword().equals(user.getPassword())) {
            mm.setStateCode(200);
            mm.setMessage("登录成功，正在跳转...");
        } else {
            mm.setStateCode(400);
            mm.setMessage("密码错误，请重试");
            return mm;
        }
        mm.setData(mapperUser);
        return mm;
    }
}
