package com.hc.commons.dto.auth;

import lombok.Data;

import java.util.List;

@Data
public class AccessTokenUser {
    private String username;
    List<String> roles;
}
