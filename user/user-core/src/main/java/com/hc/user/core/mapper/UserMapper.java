package com.hc.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.user.core.model.auth.SimpleAuthUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    SimpleAuthUserInfo queryAuthUser(@Param("phoneNum") String phoneNum);

    void insertUser(@Param("userId") String userId, @Param("phoneNum") String phoneNum);

}
