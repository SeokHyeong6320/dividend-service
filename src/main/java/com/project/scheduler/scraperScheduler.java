package com.project.scheduler;

import com.project.domain.Company;
import com.project.domain.Dividend;
import com.project.repository.CompanyRepository;
import com.project.repository.DividendRepository;
import com.project.scrap.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableScheduling
@EnableCaching
@RequiredArgsConstructor
public class scraperScheduler {

    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;
    private final Scrapper yahooScrapper;

    @CacheEvict(value = "finance", allEntries = true)
    @Scheduled(cron = "${scheduler.scrap.yahoo}")
    public void yahooFinanceScheduling() {

        List<Company> allCompanies = companyRepository.findAll();

        for (Company company : allCompanies) {
            List<Dividend> scrapedList = yahooScrapper.scrap(company);
            company.setDividends(scrapedList);
            for (Dividend dividend : scrapedList) {
                if (dividendRepository
                        .existsByCompanyIdAndDate(
                                dividend.getCompany().getId(),
                                dividend.getDate()
                        )) {
                    dividendRepository.save(dividend);
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
