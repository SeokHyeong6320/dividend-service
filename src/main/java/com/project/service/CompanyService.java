package com.project.service;

import com.project.domain.Company;
import com.project.domain.CompanyDividendInfo;
import com.project.domain.ScrapedResult;
import com.project.dto.DividendDto;
import com.project.exception.CompanyException;
import com.project.repository.CompanyRepository;
import com.project.repository.DividendRepository;
import com.project.scrap.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scrapper scrapper;

    public void save(String ticker) {
        addDividendInfo(addCompanyInfo(ticker));
    }

    private Company addCompanyInfo(String ticker) {

        if (companyRepository.existsByTicker(ticker)) {
            throw new CompanyException("already exist company info");
        }

        return companyRepository.save(scrapper.scrapCompanyByTicker(ticker));
    }

    private void addDividendInfo(Company company) {
        ScrapedResult scrapedResult = scrapper.scrap(company);

        companyRepository.save(scrapedResult.getCompany());
        dividendRepository.saveAll(scrapedResult.getDividendList());

    }

    public CompanyDividendInfo findAllCompany(String companyName) {
        Company findCompany = companyRepository.findByName(companyName)
                .orElseThrow(() ->
                        new CompanyException("not exist company in db"));

        List<DividendDto> list = dividendRepository.findAllByCompany(findCompany).stream().map(DividendDto::fromEntity).collect(Collectors.toList());

        return new CompanyDividendInfo(findCompany, list);
    }
}
