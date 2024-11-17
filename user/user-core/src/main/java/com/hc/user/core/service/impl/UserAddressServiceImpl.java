package com.hc.user.core.service.impl;

import com.hc.user.core.entities.UserAddressEntity;
import com.hc.user.core.mapper.UserAddressMapper;
import com.hc.user.core.mapper.UserMapper;
import com.hc.user.core.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper addressMapper;

    @Override
    public List<UserAddressEntity> list() {
        List<UserAddressEntity> list = addressMapper.addressList("1");

        return list;
    }

    @Override
    @Transactional
    public void setDefault(String id) {
        // todo 检验当前登录用户下是否存在默认地址
        // todo 一个用户只能有一个默认地址
        addressMapper.resetOtherDefault(id);
        addressMapper.setDefault(id);
    }
}
