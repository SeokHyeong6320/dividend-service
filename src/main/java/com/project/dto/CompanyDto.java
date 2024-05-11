package com.project.dto;

import com.project.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private Long id;
    private String name;
    private String ticker;

    public static CompanyDto fromEntity(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .ticker(company.getTicker())
                .build();
    }

    public Company toEntity() {
        return Company.builder()
                .id(id)
                .name(name)
                .ticker(ticker)
                .build();
    }

}
