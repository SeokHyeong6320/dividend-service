package com.project.service;

import com.project.domain.Company;
import com.project.dto.CompanyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    CompanyDto saveCompany(String ticker);

    Page<CompanyDto> findAllCompanyList(Pageable pageable);

    List<String> findAutoComplete(String prefix);

    void deleteCompany(String ticker);
}
