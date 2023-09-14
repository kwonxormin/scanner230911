package com.scanner230911.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scanner230911.dto.ResponseData;
import com.scanner230911.service.BarcodeService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequestMapping("/api/v1/scanner")
@RestController
@RequiredArgsConstructor
public class BarcodeController {
    @Autowired
    BarcodeService barcodeService;

    @PostMapping("/select")
    public ResponseEntity<ResponseData> findByBacode(@ModelAttribute("barcode") String barcode) throws Exception{
        return barcodeService.findByBacode(barcode);
    }
}