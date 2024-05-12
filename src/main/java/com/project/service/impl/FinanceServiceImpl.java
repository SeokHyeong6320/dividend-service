package com.project.service.impl;

import com.project.domain.Company;
import com.project.domain.CompanyDividendInfo;
import com.project.dto.CompanyDto;
import com.project.dto.DividendDto;
import com.project.exception.ErrorCode;
import com.project.exception.ServiceException;
import com.project.repository.CompanyRepository;
import com.project.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyDividendInfo findAllCompanyAndDividend(String companyName) {
        Company findCompany = companyRepository.findByName(companyName)
                .orElseThrow(() ->
                        new ServiceException(ErrorCode.COMPANY_NOT_FOUND));

        List<DividendDto> list =
                findCompany
                        .getDividends().stream()
                        .map(DividendDto::fromEntity).collect(Collectors.toList());

        return new CompanyDividendInfo(CompanyDto.fromEntity(findCompany), list);
    }
}
