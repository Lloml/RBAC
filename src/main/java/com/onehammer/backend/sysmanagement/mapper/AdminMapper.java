package com.onehammer.backend.sysmanagement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onehammer.backend.sysmanagement.domain.Admin;
import com.onehammer.backend.sysmanagement.domain.AdminPermission;
import com.onehammer.backend.sysmanagement.domain.AdminRole;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    int deleteByPrimaryKey(Long id);

    @Override
    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Integer selectIdByUsername(String username);

    int deleteByUsername(String username);

    //以下为角色操作
    int insertRoles(@Param("adminId") Long adminId, @Param("roleIds") List<Long> roleIds);

    int deleteRolesByIds(@Param("adminId") Long adminId, @Param("roleIds") List<Long> roleId);

    List<AdminRole> selectRolesById(@Param("id") Long id);

    //获取所有权限
    List<AdminPermission> selectPermissionById(@Param("id") Long id);



}