package com.hc.user.core.entities;

import lombok.Data;

@Data
public class UserAddressEntity {

    private String id;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String detail;
    private int isDefault;

}
