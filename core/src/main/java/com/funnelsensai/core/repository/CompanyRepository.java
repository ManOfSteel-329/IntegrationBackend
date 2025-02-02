package com.funnelsensai.core.repository;

import com.funnelsensai.core.domain.Company;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String name);

    Company findById(long id);

    @NotNull List<Company> findAll();
}
