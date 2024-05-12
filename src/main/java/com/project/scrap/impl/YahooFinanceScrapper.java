package com.project.scrap.impl;

import com.project.constants.Month;
import com.project.domain.Company;
import com.project.domain.Dividend;
import com.project.dto.CompanyDto;
import com.project.exception.ServerException;
import com.project.scrap.Scrapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.project.exception.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class YahooFinanceScrapper implements Scrapper {

    @Value("${scrapper.YahooFinance.info-url}")
    public String INFO_URL;
    @Value("${scrapper.YahooFinance.detail-url}")
    public String DETAIL_URL;

    @Value("${scrapper.YahooFinance.start-time}")
    private long START_TIME;

    @Override
    public List<Dividend> scrap(CompanyDto companyDto) {

        long now = System.currentTimeMillis() / 1000;

        String url =
                String.format(DETAIL_URL, companyDto.getTicker(), START_TIME, now);


        try {
            Document document = Jsoup.connect(url).get();
            Elements findParsed = document.getElementsByAttributeValue("data-testid", "history-table");
            Element tbody = findParsed.get(0).children().get(2);

            List<Dividend> list = new ArrayList<>();
            for (Element e : tbody.getAllElements()) {
                String text = e.text();
                if (!text.endsWith("Dividend") || text.length() < 17) {
                    continue;
                }

                String[] split = text.split(" ");
                int month = Month.strToMonth(split[0]);
                int day = Integer.parseInt(split[1].replace(",", ""));
                int year = Integer.parseInt(split[2]);

                String dividend = split[3];

                if (month < 0) {
                    throw new RuntimeException("Unexpected Month enum value -> " + month);
                }

                list.add(Dividend.builder()
                        .company(companyDto.toEntity())
                        .date(LocalDate.of(year, month, day))
                        .dividend(dividend)
                        .build());
            }

            return list;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {

        String url = String.format(INFO_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();
            Element titleEle;

            try {
                titleEle = document.getElementsByClass("svelte-3a2v0c")
                        .getFirst();
            } catch (NoSuchElementException e) {
                throw new ServerException(TICKER_INACCURATE);
            }

            String wholeTitle = titleEle.text();
            String companyName =
                    wholeTitle.substring(0, wholeTitle.lastIndexOf(" "));

            return new Company(companyName, ticker);


        } catch (IOException e) {
            throw new ServerException(SCRAPPER_CONNECTION_FAIL);
        }

    }
}
