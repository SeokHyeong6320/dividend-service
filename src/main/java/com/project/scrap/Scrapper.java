package com.project.scrap;

import com.project.domain.Company;
import com.project.domain.Dividend;
import com.project.domain.ScrapedResult;

import java.util.List;

public interface Scrapper {
    List<Dividend> scrap(Company company);

    Company scrapCompanyByTicker(String ticker);
}
