package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.repository.CompanyRepository;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;



    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(String name, String stripeCustomerId, String stripeSubscriptionId) {
        Company company = new Company(name, stripeCustomerId, stripeSubscriptionId);
        return companyRepository.save(company);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }


    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
