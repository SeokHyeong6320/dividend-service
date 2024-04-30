package com.project.repository;

import com.project.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByTicker(String ticker);
    Company findByTicker(String ticker);

    Optional<Company> findByName(String companyName);
}
