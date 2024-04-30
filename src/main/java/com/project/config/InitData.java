package com.project.config;

import com.project.service.CompanyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitData {

    private final CompanyService companyService;

    @PostConstruct
    public void init() {
//        companyService.save("COKE");
        companyService.save("MMM");
        companyService.save("QQQ");
        companyService.save("NKE");
        companyService.save("SPY");
        companyService.save("T");
        companyService.save("IBM");
        companyService.save("INTC");
        companyService.save("AAPL");
    }
}
