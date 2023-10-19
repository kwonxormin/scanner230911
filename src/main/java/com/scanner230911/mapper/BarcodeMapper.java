package com.scanner230911.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BarcodeMapper {
    public List<HashMap<String, Object>> findByBacode(HashMap<String, Object> params);
    public List<HashMap<String, Object>> findByModelNo(HashMap<String, Object> params);
}
