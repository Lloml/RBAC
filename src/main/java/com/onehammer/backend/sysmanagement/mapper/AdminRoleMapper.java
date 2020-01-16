package com.onehammer.backend.sysmanagement.mapper;

import com.onehammer.backend.sysmanagement.domain.AdminPermission;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onehammer.backend.sysmanagement.domain.AdminRole;

import java.util.List;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    int update(@Param("updated") AdminRole updated);

    int updateById(@Param("updated") AdminRole updated);

    int insertPermissions(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    int deletePermissionsByIds(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    List<AdminPermission> selectPermissionsById(@Param("id") Long id);


}