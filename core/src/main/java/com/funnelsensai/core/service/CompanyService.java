package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(String companyName) {
        Company newCompany = new Company(companyName);
        companyRepository.save(newCompany);
        return newCompany;
    }
    public Company findCompanyByName( String companyName) {
        return companyRepository.findByName(companyName).orElse(null);
    }
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
}
