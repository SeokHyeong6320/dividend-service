package com.project.service;

import com.project.domain.DividendInfoResponse;

public interface FinanceService {

    DividendInfoResponse findAllCompanyAndDividend(String companyName);
}
