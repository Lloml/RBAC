package com.onehammer.backend.sysmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onehammer.backend.sysmanagement.mapper.AdminPermissionMapper;
import com.onehammer.backend.sysmanagement.domain.AdminPermission;
@Service
public class AdminPermissionService extends ServiceImpl<AdminPermissionMapper, AdminPermission> {
    @Resource
    private AdminPermissionMapper adminPermissionMapper;

    public HashSet<String> getPermissionListByAdminName(String username){
        return adminPermissionMapper.getPermissionListByAdminName(username);
    }


    public int updateById(AdminPermission updated, Long id) {
        return adminPermissionMapper.updateById(updated, id);
    }


}
