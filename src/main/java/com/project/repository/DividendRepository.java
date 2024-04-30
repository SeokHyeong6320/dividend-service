package com.project.repository;

import com.project.domain.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, LocalDate> {
}
