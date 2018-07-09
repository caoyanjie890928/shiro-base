package com.auttle.shiro.service;

import com.auttle.shiro.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService extends IService<User> {

    PageInfo<User> selectByPage(User user,int start,int length);

    User selectByUsername(String username);

    void delUser(Integer userid);
}
