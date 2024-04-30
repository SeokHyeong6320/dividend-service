package com.project.dto;

import com.project.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DividendDto {

    private Company company;

    private LocalDate date;
    private Double dividend;
}
