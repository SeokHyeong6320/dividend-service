package com.project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "dividends")
@Builder
public class Company {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    private String name;
    private String ticker;

    @OneToMany(mappedBy = "company")
    private List<Dividend> dividends = new ArrayList<>();

    public Company(String name, String ticker) {
        this.name = name;
        this.ticker = ticker;
    }
}
