package com.scanner230911.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    // @RequestMapping(value="/select", method = RequestMethod.POST)
    // // @GetMapping
    // // @RequestMapping(value = "/select")
    // public HashMap<String, Object> getList(@ModelAttribute("date") String date) throws Exception{
    //     HashMap<String,Object> result = new HashMap<String,Object>();
    //     HashMap<String, Object> body = new HashMap<String, Object>();

    //     System.out.println("/scanner/select");
    //     body.put("date", date);
    //     result.put("data", commonMapper.getList(body));

    //     return result;
    // }

    @GetMapping("/test")
    public ResponseEntity<ResponseData> test(@ModelAttribute("date") String date) throws Exception{
        List<HashMap<String, Object>> result = new ArrayList<>();
        
        System.out.println(date);

        return ResponseEntity.ok().body(
                ResponseData.builder().code(200).msg("조회 성공").data(result).build()
        );
    }

    @PostMapping("/select")
    public ResponseEntity<ResponseData> getList(@ModelAttribute("date") String date) throws Exception{
        HashMap<String, Object> params = new HashMap<>();
        List<HashMap<String, Object>> result = new ArrayList<>();
        
        try {
            params.put("date", date);
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

    @PostMapping("/update")
    public ResponseEntity<Object> updateList(@ModelAttribute("date") String date) throws Exception{
        HashMap<String, Object> body = new HashMap<>();

        try {
            body.put("date", date);
            commonMapper.updateList(body);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(
                    ResponseData.builder().msg(e.toString()).code(500).build()
            );
        }

        return ResponseEntity.ok().body(
                ResponseData.builder().code(200).build()
        );
    }
}