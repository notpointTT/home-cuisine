package com.hc.auth.user.core.service.impl;

import com.hc.auth.user.core.entities.UserAddressEntity;
import com.hc.auth.user.core.mapper.UserAddressMapper;
import com.hc.auth.user.core.service.UserAddressService;
import com.hc.commons.core.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserAddressEntity detail(String id) {
        return addressMapper.detail(id);
    }

    @Override
    @Transactional
    public void update(String id, UserAddressEntity address) {
        address.setId(id);
        addressMapper.updateById(address);
        if (address.getIsDefault() == 1) {
            setDefault(id);
        }
    }

    @Override
    @Transactional
    public void add(UserAddressEntity address) {
        String id = UUIDUtil.uuid();
        address.setId(id);
        address.setUserId("1");
        addressMapper.insert(address);
        if (address.getIsDefault() == 1) {
            setDefault(id);
        }
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
