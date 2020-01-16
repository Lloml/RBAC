package com.onehammer.backend.sysmanagement.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.onehammer.backend.common.Enums.ResultEnum;
import com.onehammer.backend.common.VO.ResultVO;
import com.onehammer.backend.sysmanagement.domain.Admin;
import com.onehammer.backend.sysmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    @ResponseBody
    public Map<String, Object> selectAdmin() {
        List<Admin> result = adminService.list(null);
        return ResultVO.success(result);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> selectAdminByUsername(@PathVariable long id) {
        Admin admin = adminService.selectByPrimaryKey(id);
        if (admin != null) {
            return ResultVO.success(admin);
        } else {
            return ResultVO.failure("不存在该用户");
        }
    }

    @PostMapping
    @ResponseBody
    public Map<String, Object> addAdmin(Admin admin) {
        String message = adminService.verify(admin, "post");
        if (message.equals(ResultEnum.SUCCESS.getMessage())) {
            adminService.save(admin);
            return ResultVO.success();
        } else {
            return ResultVO.failure(message);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> updatePatchAdmin(Admin admin, @PathVariable long id) {

        String message = adminService.verify(admin, "post");

        if (message.equals("账户或者密码为空")) {
            return ResultVO.failure(message);
        }

        admin.setId(id);
        if (adminService.updateById(admin)) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("不存在该用户");
        }
    }

    @PutMapping
    @ResponseBody
    public Map<String, Object> updateAdmin(Admin admin) {

        String message = adminService.verify(admin, "post");

        if (message.equals("账户或者密码为空")) {
            return ResultVO.failure(message);
        }

        if (adminService.updateByPrimaryKey(admin) == 1) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("找不到该管理员");
        }
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> deleteAdminByUsername(@PathVariable long id) {
        int result = adminService.deleteByPrimaryKey(id);
        if (result == 0) {
            return ResultVO.failure("不存在该用户");
        } else {
            return ResultVO.success();
        }
    }

    @PostMapping("/{adminId}/role")
    @ResponseBody
    public Map<String, Object> addRolesByIds(@PathVariable Long adminId, @RequestParam List<Long> roleIds) {

        if (adminId == null || roleIds == null) {
            return ResultVO.failure("用户id或者角色id为空");
        }

        if (adminService.insertRoles(adminId, roleIds) > 0) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("失败");
        }
    }

    @DeleteMapping("/{adminId}/role")
    @ResponseBody
    public Map<String, Object> deleteRolesByIds(@PathVariable Long adminId, @RequestParam List<Long> roleIds) {
        if (adminId == null || roleIds == null) {
            return ResultVO.failure("用户id或者角色id为空");
        }

        if (adminService.deleteRolesByIds(adminId, roleIds) > 0) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("失败");
        }
    }

    @GetMapping("/{adminId}/role")
    @ResponseBody
    public Map<String, Object> getRolesById(@PathVariable Long adminId) {

        return ResultVO.success(adminService.selectRolesById(adminId));
    }

    @GetMapping("/{adminId}/permission")
    public Map<String, Object> getPermissionById(@PathVariable Long adminId) {

        return ResultVO.success(adminService.selectPermissionById(adminId));
    }

}
