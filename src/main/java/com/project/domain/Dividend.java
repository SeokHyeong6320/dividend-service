package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dividend {

    @Id
    private LocalDate date;

    private String dividend;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
