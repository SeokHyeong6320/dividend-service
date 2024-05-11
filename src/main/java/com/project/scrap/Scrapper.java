package com.project.scrap;

import com.project.domain.Company;
import com.project.domain.Dividend;
import com.project.dto.CompanyDto;

import java.util.List;

public interface Scrapper {
    List<Dividend> scrap(CompanyDto companyDto);

    Company scrapCompanyByTicker(String ticker);
}
