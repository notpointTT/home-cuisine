package com.hc.user.core.entities;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_address")
public class UserAddressEntity {

    @TableId
    private String id;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String detail;
    private int isDefault;
    private String userId;

}
