package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.dto.ResponseDto.CompanyResponseDTO;
import com.funnelsensai.core.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CompanyResponseDTO createCompany(String companyName) {
        Optional<Company> existingCompany = companyRepository.findByName(companyName);
        if (existingCompany.isPresent()) {
            throw new RuntimeException("Company '" + companyName + "' is already taken.");
        }

        Company newCompany = new Company(companyName);
        companyRepository.save(newCompany);

        return new CompanyResponseDTO(newCompany);
    }

    public Optional<Company> findCompanyByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
}
