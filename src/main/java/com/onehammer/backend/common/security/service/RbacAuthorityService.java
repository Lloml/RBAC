package com.onehammer.backend.common.security.service;

import com.onehammer.backend.sysmanagement.service.AdminPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 权限访问控制
 */
@Component("rbacauthorityservice")
public class RbacAuthorityService {
    @Autowired
    private AdminPermissionService adminPermissionService;
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission  = false;

        if (userInfo instanceof UserDetails) {

            String username = ((UserDetails) userInfo).getUsername();

            //获取资源
            Set<String> urls = adminPermissionService.getPermissionListByAdminName(username);
            // 这些 url 都是要登录后才能访问，且其他的 url 都不能访问！


            AntPathMatcher antPathMatcher = new AntPathMatcher();

            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    System.out.println(request.getRequestURI());
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }
}
