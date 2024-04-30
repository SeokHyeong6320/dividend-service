package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> getAutoComplete() {

        return null;
    }

    @GetMapping
    public ResponseEntity<?> getCompanyList(Pageable pageable) {

        return ResponseEntity.ok(companyService.findAllCompanyList(pageable));
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestParam String ticker) {
        companyService.save(ticker);
        return null;
    }

    @DeleteMapping("/{ticker}")
    public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {

        return null;
    }


}
