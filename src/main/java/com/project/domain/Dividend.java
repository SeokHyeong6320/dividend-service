package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dividend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDate date;

    private Double dividend;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
