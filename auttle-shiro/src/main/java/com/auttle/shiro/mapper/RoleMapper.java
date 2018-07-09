package com.auttle.shiro.mapper;

import com.auttle.shiro.model.Role;
import com.auttle.shiro.util.MyMapper;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {
    public List<Role> queryRoleListWithSelected(Integer id);
}