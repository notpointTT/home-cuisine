package com.hc.user.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.user.core.entities.UserAddressEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressMapper extends BaseMapper<UserAddressEntity> {

    List<UserAddressEntity> addressList(@Param("userId") String userId);

    UserAddressEntity detail(@Param("id") String id);

    void resetOtherDefault(@Param("id") String id);

    void setDefault(@Param("id") String id);

}
