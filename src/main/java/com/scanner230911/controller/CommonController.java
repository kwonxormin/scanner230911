package com.scanner230911.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scanner230911.dto.ResponseData;
import com.scanner230911.mapper.CommonMapper;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/api/v1/scanner")
@RestController
@RequiredArgsConstructor
public class CommonController {
    private final CommonMapper commonMapper;

    @PostMapping("/select")
    public ResponseEntity<ResponseData> getList(@ModelAttribute("barcode") String barcode) throws Exception{
        HashMap<String, Object> params = new HashMap<>();
        List<HashMap<String, Object>> result = new ArrayList<>();
        
        try {
            System.out.println(barcode);
            params.put("barcode", barcode);
            result = commonMapper.getList(params);

            if(result.isEmpty()){
                return ResponseEntity.ok().body(
                        ResponseData.builder().code(200).msg("조회 결과 없음").build()
                );
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    ResponseData.builder().msg(e.toString()).code(500).build()
            );
        }

        return ResponseEntity.ok().body(
                ResponseData.builder().code(200).msg("조회 성공").data(result).build()
        );
    }
}