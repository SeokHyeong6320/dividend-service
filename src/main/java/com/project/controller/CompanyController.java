package com.project.controller;

import com.project.domain.Company;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/autocomplete")
    public ResponseEntity<?> getAutoComplete(@RequestParam String prefix) {

        return ResponseEntity.ok(companyService.findAutoComplete(prefix));
    }

    @GetMapping
    public ResponseEntity<?> getCompanyList(Pageable pageable) {

        return ResponseEntity.ok(companyService.findAllCompanyList(pageable));
    }

    @PostMapping
    public void addCompany(@RequestParam String ticker) {
        Company savedCompany = companyService.save(ticker);
        companyService.addAutoCompleteKeyword(savedCompany.getName());
    }

    @DeleteMapping("/{ticker}")
    public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {

        return null;
    }


}
