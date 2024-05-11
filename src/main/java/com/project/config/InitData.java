package com.project.config;

import com.project.service.impl.CompanyServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitData {

    private final CompanyServiceImpl companyServiceImpl;

    @PostConstruct
    public void init() {
//        companyService.saveCompany("COKE");
//        companyService.saveCompany("MMM");
//        companyService.saveCompany("QQQ");
//        companyService.saveCompany("NKE");
//        companyService.saveCompany("SPY");
//        companyService.saveCompany("T");
//        companyService.saveCompany("IBM");
//        companyService.saveCompany("INTC");
//        companyService.saveCompany("AAPL");
    }
}
