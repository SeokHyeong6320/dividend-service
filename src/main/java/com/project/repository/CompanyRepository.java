package com.project.repository;

import com.project.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByTicker(String ticker);
    Optional<Company> findByTicker(String ticker);

    Optional<Company> findByName(String companyName);

    Page<Company> findAll(Pageable pageable);

    void deleteByTicker(String ticker);
}
