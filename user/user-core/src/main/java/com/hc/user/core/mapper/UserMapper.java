package com.hc.user.core.mapper;

import com.hc.user.core.entities.UserEntity;
import com.hc.user.core.model.auth.AuthUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    void insertUser(@Param("userId") String userId, @Param("phoneNum") String phoneNum);

    UserEntity queryByUsername(@Param("username") String username);

    UserEntity queryByPhoneNum(@Param("phoneNum") String phoneNum);

}
