package com.project.service.impl;

import com.project.constants.CacheKey;
import com.project.domain.Company;
import com.project.domain.DividendInfoResponse;
import com.project.dto.CompanyDto;
import com.project.dto.DividendDto;
import com.project.exception.ErrorCode;
import com.project.exception.ServiceException;
import com.project.repository.CompanyRepository;
import com.project.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final CompanyRepository companyRepository;


    @Override
    @Cacheable(key = "#companyName", value = CacheKey.KEY_FINANCE)
    public DividendInfoResponse findAllCompanyAndDividend(String companyName) {
        Company findCompany = companyRepository.findByName(companyName)
                .orElseThrow(() ->
                        new ServiceException(ErrorCode.COMPANY_NOT_FOUND));

        List<DividendDto> list =
                findCompany
                        .getDividends().stream()
                        .map(DividendDto::fromEntity).collect(Collectors.toList());

        return new DividendInfoResponse(CompanyDto.fromEntity(findCompany), list);
    }
}
