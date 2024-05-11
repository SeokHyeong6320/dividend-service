package com.project.service;

import com.project.domain.CompanyDividendInfo;

public interface FinanceService {

    CompanyDividendInfo findAllCompanyAndDividend(String companyName);
}
