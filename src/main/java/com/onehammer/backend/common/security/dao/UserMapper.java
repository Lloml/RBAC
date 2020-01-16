package com.onehammer.backend.common.security.dao;

import com.onehammer.backend.common.security.entity.SelfUserDetails;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @description: 用户dao层
 */
@Component
public interface UserMapper {

    //通过username查询用户
    SelfUserDetails getUser(@Param("username") String username);

}
