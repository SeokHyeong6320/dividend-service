package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PreAuthorize("hasRole('WRITE')")
    @PostMapping
    public void addCompany(@RequestParam String ticker) {
        companyService.saveCompany(ticker);
    }

    @PreAuthorize("hasRole('READ')")
    @GetMapping
    public ResponseEntity<Page<CompanyDto>> getCompanyList(Pageable pageable) {

        return ResponseEntity.ok(companyService.findAllCompanyList(pageable));
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{ticker}")
    public void deleteCompany(@PathVariable String ticker) {

        companyService.deleteCompany(ticker);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> getAutoComplete(@RequestParam String prefix) {

        return ResponseEntity.ok(companyService.findAutoComplete(prefix));
    }


}
