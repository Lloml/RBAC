package com.onehammer.backend.sysmanagement.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onehammer.backend.sysmanagement.domain.AdminPermission;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

public interface AdminPermissionMapper extends BaseMapper<AdminPermission> {
    HashSet<String> getPermissionListByAdminName(String username);

    int updateById(@Param("updated") AdminPermission updated, @Param("id") Long id);





}