package com.auttle.shiro.service.impl;

import com.auttle.shiro.mapper.ResourcesMapper;
import com.auttle.shiro.model.Resources;
import com.auttle.shiro.service.ResourcesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class ResourceServiceImpl extends BaseService<Resources> implements ResourcesService {

    @Resource
    private ResourcesMapper resourcesMapper;

    @Override
    public PageInfo<Resources> selectbyPage(Resources resources, int start, int length) {
        int page = start/length + 1;
        Example example = new Example(Resources.class);

        PageHelper.startPage(page,length);

        List<Resources> userList = selectByExample(example);

        return new PageInfo<>(userList);
    }

    @Override
    public List<Resources> queryAll() {
        return resourcesMapper.selectAll();
    }

    @Override
    public List<Resources> loadUserResources(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Resources> queryResourcesListWithSelected(Integer rid) {
        return null;
    }
}
