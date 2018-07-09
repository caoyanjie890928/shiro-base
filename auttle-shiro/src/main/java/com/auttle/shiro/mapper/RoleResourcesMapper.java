package com.auttle.shiro.mapper;

import com.auttle.shiro.model.RoleResources;
import java.util.List;

import com.auttle.shiro.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface RoleResourcesMapper extends MyMapper<RoleResources> {
    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("resourcesid") Integer resourcesid);

    int insert(RoleResources record);

    List<RoleResources> selectAll();
}