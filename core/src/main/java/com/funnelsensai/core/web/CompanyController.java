package com.funnelsensai.core.web;


import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.dto.CompanyResponseDTO;
import com.funnelsensai.core.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/createCompany")
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company.getName());
    }

    @GetMapping("/getCompanyByName")
    public ResponseEntity<CompanyResponseDTO> getCompany(@RequestParam String name) {
        //Using a Dto of company so we can return a message if the company doesn't exist.
        Company foundCompany = companyService.findCompanyByName(name);
        if (foundCompany == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CompanyResponseDTO("Company doesn't exist"));
        }
        return ResponseEntity.ok(new CompanyResponseDTO(foundCompany));
    }

    @GetMapping("/getCompanyById")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@RequestParam Long id) {
        //Using a Dto of company so we can return a message if the company doesn't exist.
        Company foundCompany = companyService.findCompanyById(id);
        if (foundCompany == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CompanyResponseDTO("Company doesn't exist"));
        }
        return ResponseEntity.ok(new CompanyResponseDTO(foundCompany));
    }

    @GetMapping("/getAllCompanies")
    public List<Company> getAllCompanies() {
        return companyService.findAll();
    }
}
