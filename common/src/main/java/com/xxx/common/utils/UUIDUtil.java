package com.xxx.common.utils;

import java.util.UUID;

/**
 * @author a1234
 * @description
 * @create 2023-12-26 18:08
 */
public final class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
