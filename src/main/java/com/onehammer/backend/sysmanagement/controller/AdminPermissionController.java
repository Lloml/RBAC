package com.onehammer.backend.sysmanagement.controller;

import com.onehammer.backend.common.VO.ResultVO;
import com.onehammer.backend.sysmanagement.domain.Admin;
import com.onehammer.backend.sysmanagement.domain.AdminPermission;
import com.onehammer.backend.sysmanagement.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * demo
 * 10/23/2019 5:48 PM
 * 权限管理
 *
 * @author Lloml
 **/

@RestController
@RequestMapping("/AdminPermission")
public class AdminPermissionController {
    @Autowired
    private AdminPermissionService adminPermissionService;

    @GetMapping
    @ResponseBody
    public Map<String, Object> getAdminPermission() {
        List<AdminPermission> result = adminPermissionService.list();
        return ResultVO.success(result);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> getAdminPermissionByName(@PathVariable long id) {
        AdminPermission adminPermission = adminPermissionService.getById(id);
        if (adminPermission != null) {
            return ResultVO.success(adminPermission);
        } else {
            return ResultVO.failure("没有这个名称的权限");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> deleteAdminPermission(@PathVariable long id) {

        if (adminPermissionService.removeById(id)) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("删除失败,可能没有找到这个权限");
        }

    }

    @PostMapping
    @ResponseBody
    public Map<String, Object> addAdminPermission(AdminPermission adminPermission) {

        if (adminPermissionService.save(adminPermission)) {
            return ResultVO.success();
        } else {
            return ResultVO.failure();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> updatePatchAdminPermission(AdminPermission adminPermission, @PathVariable long id) {
        adminPermission.setId(id);
        if (adminPermissionService.updateById(adminPermission)) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("找不到这种权限");
        }
    }

    @PutMapping
    @ResponseBody
    public Map<String, Object> updateAdminPermission(AdminPermission adminPermission) {
        if (adminPermissionService.updateById(adminPermission, adminPermission.getId()) == 1) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("找不到这种权限");
        }

    }
}
