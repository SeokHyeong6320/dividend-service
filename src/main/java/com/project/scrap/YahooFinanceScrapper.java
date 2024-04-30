package com.project.scrap;

import com.project.constants.Month;
import com.project.domain.Company;
import com.project.domain.Dividend;
import com.project.domain.ScrapedResult;
import com.project.exception.CompanyException;
import com.project.repository.DividendRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.time.LocalDate.*;

@Component
@RequiredArgsConstructor
public class YahooFinanceScrapper implements Scrapper{

    public static final String INFO_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    public static final String DETAIL_URL = "https://finance.yahoo.com/quote/%s/history?frequency=1mo&period1=%d&period2=%d";

    private static final long START_TIME = 86400;

    @Override
    public List<Dividend> scrap(Company company) {

        long now = System.currentTimeMillis() / 1000;

        String url =
                String.format(DETAIL_URL, company.getTicker(), START_TIME, now);


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
                                .company(company)
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
                titleEle = document.getElementsByClass("svelte-ufs8hf")
                        .getFirst();
            } catch (NoSuchElementException e) {
                throw new CompanyException("inaccurate company ticker", e);
            }

            String wholeTitle = titleEle.text();
            String companyName =
                    wholeTitle.substring(0, wholeTitle.lastIndexOf(" "));

            return new Company(companyName, ticker);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
