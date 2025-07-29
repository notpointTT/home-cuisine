package com.hc.user.core.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author a1234
 * @description
 * @create 2025-07-23 18:19
 */
@Data
@TableName("user")
public class UserEntity {

    @Id
    private Long id;

    private String phoneNum;

    private String username;

    private String displayName;

    private String passwordHash;

    private Boolean mobileVerified = false;

    private Date createdAt;


}
