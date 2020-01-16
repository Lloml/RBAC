package com.onehammer.backend.sysmanagement.service;

import com.onehammer.backend.sysmanagement.domain.AdminPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onehammer.backend.sysmanagement.mapper.AdminRoleMapper;
import com.onehammer.backend.sysmanagement.domain.AdminRole;

import java.util.List;

@Service
public class AdminRoleService extends ServiceImpl<AdminRoleMapper, AdminRole> {

    @Resource
    private AdminRoleMapper adminRoleMapper;

    public int updatePatchById(AdminRole updated) {
        return adminRoleMapper.updateById(updated);
    }


    public int update(AdminRole updated) {
        return adminRoleMapper.update(updated);
    }

    public int insertPermissions(Long roleId, List<Long> permissionIds) {

        return adminRoleMapper.insertPermissions(roleId, permissionIds);

    }

    public int deletePermissionsByIds(Long roleId, List<Long> permissionIds) {
        return adminRoleMapper.deletePermissionsByIds(roleId, permissionIds);
    }

    public List<AdminPermission> selectPermissionsById(Long id) {
        return adminRoleMapper.selectPermissionsById(id);
    }


}
