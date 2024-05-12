package com.project.service.impl;

import com.project.constants.CacheKey;
import com.project.domain.Company;
import com.project.domain.Dividend;
import com.project.dto.CompanyDto;
import com.project.exception.ServerException;
import com.project.exception.ServiceException;
import com.project.repository.CompanyRepository;
import com.project.repository.DividendRepository;
import com.project.scrap.Scrapper;
import com.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Trie;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.exception.ErrorCode.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scrapper scrapper;
    private final CacheManager cacheManager;

    private final Trie<String, String> trie;

    @Override
    public CompanyDto saveCompany(String ticker) {
        Company savedCompany = addCompanyInfo(ticker);

        addDividendInfo(savedCompany);
        addAutoCompleteKeyword(savedCompany.getName());
        return CompanyDto.fromEntity(savedCompany);
    }

    @Override
    public Page<CompanyDto> findAllCompanyList(Pageable pageable) {

        return new PageImpl<>(companyRepository.findAll(pageable).stream()
                .map(CompanyDto::fromEntity)
                .collect(Collectors.toList()));
    }

    @Override
    public List<String> findAutoComplete(String prefix) {
        return trie.prefixMap(prefix)
                .keySet().stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public void deleteCompany(String ticker) {
        Company findCompany = companyRepository.findByTicker(ticker)
                .orElseThrow(() -> new ServerException(COMPANY_NOT_FOUND));

        dividendRepository.deleteByCompanyTicker(ticker);
        companyRepository.deleteByTicker(ticker);

        clearFinanceCache(findCompany.getName());

        deleteAutoCompleteKeyword(findCompany.getName());
    }

    private Company addCompanyInfo(String ticker) {

        if (companyRepository.existsByTicker(ticker)) {
            throw new ServiceException(COMPANY_ALREADY_EXIST);
        }

        return companyRepository.save(scrapper.scrapCompanyByTicker(ticker));
    }
    private void addDividendInfo(Company company) {
        List<Dividend> scrapedList =
                scrapper.scrap(CompanyDto.fromEntity(company));

        company.setDividends(scrapedList);

        dividendRepository.saveAll(company.getDividends());

    }

    private void addAutoCompleteKeyword(String companyName) {
        trie.put(companyName, null);
    }

    private void deleteAutoCompleteKeyword(String companyName) {
        trie.remove(companyName);
    }

    private void clearFinanceCache(String companyName) {
        cacheManager.getCache(CacheKey.KEY_FINANCE).evict(companyName);
    }
}
