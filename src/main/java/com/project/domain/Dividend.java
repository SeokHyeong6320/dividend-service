package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dividend {

    @Id
    private LocalDateTime date;

    private String dividend;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
