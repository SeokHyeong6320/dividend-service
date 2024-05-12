package com.project.domain;

import com.project.dto.CompanyDto;
import com.project.dto.DividendDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DividendInfoResponse {

    private CompanyDto company;

    private List<DividendDto> dividends;
}
