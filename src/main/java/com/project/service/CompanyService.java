package com.project.service;

import com.project.domain.Company;
import com.project.domain.ScrapedResult;
import com.project.exception.CompanyException;
import com.project.repository.CompanyRepository;
import com.project.repository.DividendRepository;
import com.project.scrap.Scrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scrapper scrapper;

    public void save(String ticker) {
        Company company = addCompanyInfo(ticker);
        addDividendInfo(company);
    }

    private Company addCompanyInfo(String ticker) {

        if (companyRepository.existsByTicker(ticker)) {
            throw new CompanyException("already exist company info");
        }

        return scrapper.scrapCompanyByTicker(ticker);
    }

    private void addDividendInfo(Company company) {
        ScrapedResult scrapedResult = scrapper.scrap(company);

        companyRepository.save(company);
        dividendRepository.saveAll(scrapedResult.getDividendList());

    }
}
