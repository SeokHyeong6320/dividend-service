package com.project.scrap;

import com.project.domain.Company;
import com.project.domain.ScrapedResult;

public interface Scrapper {
    Company scrap(Company company);

    Company scrapCompanyByTicker(String ticker);
}
