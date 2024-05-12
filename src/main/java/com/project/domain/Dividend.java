package com.project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "company")
@Builder
public class Dividend {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dividend_id")
    private Long id;

    @Column(name = "dividend_dt")
    private LocalDate date;

    @Column(name = "dividend_amt")
    private String dividend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;



}
