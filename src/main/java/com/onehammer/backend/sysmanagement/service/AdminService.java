package com.onehammer.backend.sysmanagement.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onehammer.backend.common.Enums.ResultEnum;
import com.onehammer.backend.common.utils.StringUtil;
import com.onehammer.backend.sysmanagement.domain.AdminPermission;
import com.onehammer.backend.sysmanagement.domain.AdminRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.onehammer.backend.sysmanagement.mapper.AdminMapper;
import com.onehammer.backend.sysmanagement.domain.Admin;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminService extends ServiceImpl<AdminMapper, Admin> {

    @Resource
    private AdminMapper adminMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public int deleteByPrimaryKey(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }


    public int insert(Admin record) {
        return adminMapper.insert(record);
    }


    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }


    public Admin selectByPrimaryKey(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }


    public boolean save(Admin admin) {
        //加密密码
        String encodePassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        return adminMapper.insert(admin) != 0;
    }

    public int selectIdByUsername(String username) {
        return adminMapper.selectIdByUsername(username);
    }

    public Admin selectByUsername(String username) {
        List<Admin> admins = adminMapper.selectByMap(new HashMap<String, Object>() {{
            put("username", username);
        }});

        if (admins.size() > 0) {
            return admins.get(0);
        } else {
            return null;
        }
    }

    public int deleteByUsername(String username) {
        return adminMapper.deleteByUsername(username);
    }

    public boolean update(Admin admin, String username) {
        //加密密码
        String encodePassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        Integer id = adminMapper.selectIdByUsername(username);
        if (id == null) {
            return false;
        }
        admin.setId((long) id);
        return adminMapper.updateById(admin) != 0;
    }

    //验证实体类是否合理
    public String verify(Admin admin, String method) {

        List<Admin> admins = adminMapper.selectByMap(new HashMap<String, Object>() {{
            put("username", admin.getUsername());
        }});

        if (StringUtil.isEmpty(admin.getUsername()) || StringUtil.isEmpty(admin.getPassword())) {
            return "账户或者密码为空";
        } else if (admins.size() != 0 && method.equals("post")) {
            return "用户名已经存在";
        } else if (admins.size() == 0 && method.equals("put")) {
            return "不存在该用户";
        } else {
            return ResultEnum.SUCCESS.getMessage();
        }
    }

    public int insertRoles(Long adminId, List<Long> roleIds) {
        return adminMapper.insertRoles(adminId, roleIds);
    }

    public int deleteRolesByIds(Long adminId, List<Long> roleIds) {
        return adminMapper.deleteRolesByIds(adminId, roleIds);
    }

    public List<AdminRole> selectRolesById(Long id) {
        return adminMapper.selectRolesById(id);
    }

    public List<AdminPermission> selectPermissionById(Long id) {
        return adminMapper.selectPermissionById(id);
    }
}
