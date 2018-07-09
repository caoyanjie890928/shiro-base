package com.auttle.shiro.service;

import com.auttle.shiro.model.Resources;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ResourcesService extends IService<Resources> {

    PageInfo<Resources> selectbyPage(Resources resources,int start,int length);

    public List<Resources> queryAll();

    public List<Resources> loadUserResources(Map<String,Object> map);

    public List<Resources> queryResourcesListWithSelected(Integer rid);
}
