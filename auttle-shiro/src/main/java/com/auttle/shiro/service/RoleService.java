package com.auttle.shiro.service;

import com.auttle.shiro.model.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService extends IService<Role> {

    public List<Role> queryRoleListWithSelected(Integer uid);

    public PageInfo<Role> selectByPage(Role role, int start, int length);

    public void deleteRole(Integer roleid);
}
