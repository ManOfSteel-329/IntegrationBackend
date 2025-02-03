package com.funnelsensai.core.web;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.dto.ResponseDto.CompanyResponseDTO;
import com.funnelsensai.core.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("auth/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody Map<String, String> request) {
        String companyName = request.get("companyName");

        if (companyName == null || companyName.isBlank()) {
            return ResponseEntity.badRequest().body(new CompanyResponseDTO(null, "Company name is required", List.of()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.createCompany(companyName));
    }

    @GetMapping("/byName")
    public ResponseEntity<CompanyResponseDTO> getCompanyByName(@RequestParam String name) {
        Optional<Company> foundCompany = companyService.findCompanyByName(name);
        return foundCompany
                .map(company -> ResponseEntity.ok(new CompanyResponseDTO(company)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CompanyResponseDTO(null, "Company doesn't exist", List.of())));
    }

    @GetMapping("/byId")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@RequestParam Long id) {
        Optional<Company> foundCompany = companyService.findCompanyById(id);
        return foundCompany
                .map(company -> ResponseEntity.ok(new CompanyResponseDTO(company)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CompanyResponseDTO(null, "Company doesn't exist", List.of())));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        List<CompanyResponseDTO> dtos = companies.stream().map(CompanyResponseDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }
}
