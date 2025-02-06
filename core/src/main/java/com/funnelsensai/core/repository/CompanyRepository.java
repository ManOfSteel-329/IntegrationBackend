package com.funnelsensai.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.funnelsensai.core.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    public Company findByName(String name);
}
