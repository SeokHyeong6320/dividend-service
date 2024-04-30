package com.project.scrap;

import com.project.domain.Company;
import com.project.domain.ScrapedResult;

public interface Scrapper {
    ScrapedResult scrap(Company company);

    Company scrapCompanyByTicker(String ticker);
}
