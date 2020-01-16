package com.onehammer.backend.sysmanagement.controller;

import com.onehammer.backend.common.VO.ResultVO;
import com.onehammer.backend.sysmanagement.domain.AdminRole;
import com.onehammer.backend.sysmanagement.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * demo
 * 10/24/2019 11:45 AM
 * 管理员角色控制类
 *
 * @author Lloml
 **/
@RestController
@RequestMapping("/AdminRole")
public class AdminRoleController {
    @Autowired
    private AdminRoleService adminRoleService;


    @PostMapping
    @ResponseBody
    public Map<String, Object> add(AdminRole adminRole) {

        if (adminRoleService.save(adminRole)) {
            return ResultVO.success();
        } else {
            return ResultVO.failure();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable String id) {
        if (adminRoleService.removeById(id)) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("没有找到个角色");
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> get(@PathVariable String id) {

        AdminRole adminRole = adminRoleService.getById(id);
        if (adminRole != null) {
            return ResultVO.success(adminRole);
        } else {
            return ResultVO.failure("找不到这个角色");
        }
    }

    @GetMapping
    @ResponseBody
    public Map<String, Object> getAll() {
        List<AdminRole> adminRoles = adminRoleService.list();

        return ResultVO.success(adminRoles);
    }

    @PutMapping
    @ResponseBody
    public Map<String, Object> update(AdminRole adminRole) {

        if (adminRoleService.update(adminRole) == 1) {
            return ResultVO.success();
        } else {
            return ResultVO.failure();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Map<String, Object> updatePatch(AdminRole adminRole, @PathVariable Long id) {
        adminRole.setId(id);
        if (adminRoleService.updatePatchById(adminRole) == 1) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("失败,可能是找不到该角色");
        }
    }

    @PostMapping("/{roleId}/permission")
    @ResponseBody
    public Map<String, Object> addPermissions(@PathVariable Long roleId, @RequestParam List<Long> permissionIds) {

        if (adminRoleService.insertPermissions(roleId, permissionIds) > 0) {
            return ResultVO.success();
        } else {
            return ResultVO.failure();
        }
    }

    @DeleteMapping("/{roleId}/permission")
    @ResponseBody
    public Map<String, Object> deletePermission(@PathVariable Long roleId, @RequestParam List<Long> permissionIds) {

        if (adminRoleService.deletePermissionsByIds(roleId, permissionIds) > 0) {
            return ResultVO.success();
        } else {
            return ResultVO.failure("没有找到任何匹配的记录");
        }
    }

    @GetMapping("/{roleId}/permission")
    public Map<String, Object> selectPermission(@PathVariable Long roleId) {

        return ResultVO.success(adminRoleService.selectPermissionsById(roleId));
    }

}
