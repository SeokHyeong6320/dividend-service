package com.project.service;

import com.project.repository.CompanyRepository;
import com.project.repository.DividendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DividendService {

    private final DividendRepository dividendRepository;
}
