package com.hc.user.core.mapper;

import com.hc.user.core.entities.UserAddressEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAddressMapper {

    List<UserAddressEntity> addressList(@Param("userId") String userId);

    void resetOtherDefault(@Param("id") String id);

    void setDefault(@Param("id") String id);

}
