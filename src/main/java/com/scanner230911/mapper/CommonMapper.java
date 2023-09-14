package com.scanner230911.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommonMapper {
    public List<HashMap<String, Object>> getList(HashMap<String, Object> body) throws Exception;
}
