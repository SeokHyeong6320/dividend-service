package com.project.service;

import com.project.domain.Company;
import com.project.domain.CompanyDividendInfo;
import com.project.dto.CompanyDto;
import com.project.dto.DividendDto;
import com.project.exception.CompanyException;
import com.project.repository.CompanyRepository;
import com.project.repository.DividendRepository;
import com.project.scrap.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Trie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scrapper scrapper;

    private final Trie<String, String> trie;

    public Company save(String ticker) {
        Company company = addCompanyInfo(ticker);
        addDividendInfo(company);
        return company;
    }

    private Company addCompanyInfo(String ticker) {

        if (companyRepository.existsByTicker(ticker)) {
            throw new CompanyException("already exist company info");
        }

        return companyRepository.save(scrapper.scrapCompanyByTicker(ticker));
    }

    private void addDividendInfo(Company company) {
        scrapper.scrap(company);


        dividendRepository.saveAll(company.getDividends());

    }

    public CompanyDividendInfo findAllCompanyAndDividend(String companyName) {
        Company findCompany = companyRepository.findByName(companyName)
                .orElseThrow(() ->
                        new CompanyException("not exist company in db"));

        List<DividendDto> list = findCompany.getDividends().stream().map(DividendDto::fromEntity).collect(Collectors.toList());

        return new CompanyDividendInfo(CompanyDto.fromEntity(findCompany), list);
    }

    public Page<CompanyDto> findAllCompanyList(Pageable pageable) {

        return new PageImpl<>(companyRepository.findAll(pageable).stream()
                .map(CompanyDto::fromEntity)
                .collect(Collectors.toList()));
    }

    public List<String> findAutoComplete(String prefix) {
        return trie.prefixMap(prefix).keySet().stream().limit(10).collect(Collectors.toList());
    }
    public void addAutoCompleteKeyword(String companyName) {
        trie.put(companyName, null);
    }

    public void deleteCompany(String ticker) {
        dividendRepository.deleteByCompanyTicker(ticker);
        companyRepository.deleteByTicker(ticker);
    }
}
