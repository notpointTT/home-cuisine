package com.xxx.mng.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a1234
 * @description
 * @create 2023-12-10 17:54
 */
@Repository
public interface SuoFundMapper {

    List<Object> selectAll();

}
