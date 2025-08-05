package com.hc.auth.user.core.mapper;

import com.hc.auth.user.core.entities.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    void insertUser(@Param("userId") String userId, @Param("phoneNum") String phoneNum);

    UserEntity queryByUsername(@Param("username") String username);

    UserEntity queryByPhoneNum(@Param("phoneNum") String phoneNum);

}
