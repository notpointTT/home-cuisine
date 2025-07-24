package com.hc.user.core.mapper;

import com.hc.user.core.model.auth.AuthUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    AuthUserInfo queryAuthUser(@Param("phoneNum") String phoneNum);

    void insertUser(@Param("userId") String userId, @Param("phoneNum") String phoneNum);

}
