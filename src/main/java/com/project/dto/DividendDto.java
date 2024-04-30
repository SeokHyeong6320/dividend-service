package com.project.dto;

import com.project.domain.Company;
import com.project.domain.Dividend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DividendDto {

    private LocalDate date;
    private String dividend;

    public static DividendDto fromEntity(Dividend dividend) {
        return DividendDto.builder()
                .date(dividend.getDate())
                .dividend(dividend.getDividend())
                .build();
    }
}
