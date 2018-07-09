package com.auttle.shiro.service.impl;

import com.auttle.shiro.mapper.RoleMapper;
import com.auttle.shiro.mapper.RoleResourcesMapper;
import com.auttle.shiro.model.Role;
import com.auttle.shiro.model.RoleResources;
import com.auttle.shiro.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleResourcesMapper roleResourcesMapper;

    @Override
    public List<Role> queryRoleListWithSelected(Integer uid) {
        return roleMapper.queryRoleListWithSelected(uid);
    }

    @Override
    public PageInfo<Role> selectByPage(Role role, int start, int length) {

        int page = start / length - 1;
        Example example = new Example(Role.class);

        PageHelper.startPage(page,length);
        List<Role> roleList = selectByExample(example);

        return new PageInfo<>(roleList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false,rollbackFor = {Exception.class})
    public void deleteRole(Integer roleid) {

        mapper.deleteByPrimaryKey(roleid);

        Example example = new Example(RoleResources.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleid",roleid);
        roleResourcesMapper.deleteByExample(example);
    }
}
