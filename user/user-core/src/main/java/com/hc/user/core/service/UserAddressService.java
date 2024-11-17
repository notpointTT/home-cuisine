package com.hc.user.core.service;

import com.hc.user.core.entities.UserAddressEntity;

import java.util.List;

public interface UserAddressService {

    List<UserAddressEntity> list();

    void setDefault(String id);

}
