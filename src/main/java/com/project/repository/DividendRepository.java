package com.project.repository;

import com.project.domain.Company;
import com.project.domain.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, Long> {

    boolean existsByCompanyAndDate(Company company, LocalDate date);
    List<Dividend> findAllByCompany(Company company);

    void deleteByCompanyTicker(String ticker);
}
