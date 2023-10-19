package com.scanner230911.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scanner230911.barcode.Barcode;
import com.scanner230911.dto.ResponseData;
import com.scanner230911.mapper.BarcodeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BarcodeService {
    @Autowired
    BarcodeMapper barcodeMapper;

    public ResponseEntity<ResponseData> findByBacode(String barcode) {
        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> params = new HashMap<>();
        System.out.println(barcode);
        
        try{
            params.put("barcode", barcode);
            
            dataList = barcodeMapper.findByBacode(params);

            // barcode 로 조회 결과 나오지 않은 경우 model_no로 다시 조회
            if(dataList.isEmpty()){
                dataList = new ArrayList<HashMap<String, Object>>();
                dataList = barcodeMapper.findByModelNo(params);

                if(dataList.isEmpty()){
                    return 
                        ResponseEntity.ok().body(
                            ResponseData.builder().code(200).msg("조회 결과 없음").build()
                        );
                }
                
                barcode = dataList.get(0).get("BARCODE").toString();
            }

            String filePath = "/home/ec2-user/upload/"+barcode+".png"; // 저장할 파일 경로
            
            Barcode.generateBarcodeImage(barcode, filePath);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(
                ResponseData.builder().msg(e.toString()).code(500).build()
            );
        }

        return ResponseEntity.ok().body(
                ResponseData.builder().code(200).msg("조회 성공").data(dataList).build()
        );
    }
}
