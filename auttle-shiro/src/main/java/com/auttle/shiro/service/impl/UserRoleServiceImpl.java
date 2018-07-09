package com.auttle.shiro.service.impl;

import com.auttle.shiro.model.UserRole;
import com.auttle.shiro.service.UserRoleService;
import com.auttle.shiro.shiro.MyShiroRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService{

    @Autowired
    private MyShiroRealm myShiroRealm;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public void addUserRole(UserRole userRole) {
        Example example = new Example(UserRole.class);

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userRole.getUserid());
        mapper.deleteByExample(example);

        String[] roleids = userRole.getRoleid().split(",");

        for (String roleId : roleids){
            UserRole u = new UserRole();
            u.setRoleid(roleId);
            u.setUserid(userRole.getUserid());
            mapper.insert(u);
        }

        List<Integer> userid = new ArrayList<Integer>();

        userid.add(userRole.getUserid());
        myShiroRealm.clearUserAuthByUserId(userid);
    }
}
