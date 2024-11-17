package com.hc.user.core.service;

import com.hc.user.core.entities.UserAddressEntity;

import java.util.List;

public interface UserAddressService {

    List<UserAddressEntity> list();

    UserAddressEntity detail(String id);

    void update(String id, UserAddressEntity address);

    void add(UserAddressEntity address);

    void setDefault(String id);

}
