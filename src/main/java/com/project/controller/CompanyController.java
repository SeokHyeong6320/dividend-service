package com.project.controller;

import com.project.domain.Company;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('READ')")
    @GetMapping
    public ResponseEntity<?> getCompanyList(Pageable pageable) {

        return ResponseEntity.ok(companyService.findAllCompanyList(pageable));
    }

    @PreAuthorize("hasRole('WRITE')")
    @PostMapping
    public void addCompany(@RequestParam String ticker) {
        Company savedCompany = companyService.save(ticker);
        companyService.addAutoCompleteKeyword(savedCompany.getName());
    }

    @PreAuthorize("hasRole('WRITE')")
    @DeleteMapping("/{ticker}")
    public void deleteCompany(@PathVariable String ticker) {

        companyService.deleteCompany(ticker);
    }


}
