package com.project.controller;

import com.project.domain.DividendInfoResponse;
import com.project.service.FinanceService;
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

    private final FinanceService financeService;

    @GetMapping("/dividend/{companyName}")
    public ResponseEntity<DividendInfoResponse>
            getCompanyDividend(@PathVariable String companyName) {

        return ResponseEntity.ok(
                financeService.findAllCompanyAndDividend(companyName)
        );
    }
}
