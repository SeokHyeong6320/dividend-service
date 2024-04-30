package com.project.controller;

import com.project.service.DividendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final DividendService dividendService;

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<?> getCompanyDividend(@PathVariable String companyName) {


        return null;
    }
}
