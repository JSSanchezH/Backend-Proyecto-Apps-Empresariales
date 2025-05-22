package com.proyect.Human_Resources.services;

import org.springframework.stereotype.Service;

import com.proyect.Human_Resources.models.UserCompany;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    private final CompanyRepository companyRepository;
    private final UserCompanyRepository userCompanyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(CompanyRepository companyRepository,
            UserCompanyRepository userCompanyRepository,
            PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.userCompanyRepository = userCompanyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerCompanyWithUser(CompanyRegisterRequest request) {

        Company company = new Company();
        company.setName(request.getCompany().getName());
        company.setNit(request.getCompany().getNit());
        company.setAddress(request.getCompany().getAddress());
        company.setEmail(request.getCompany().getEmail());
        company.setTypeIndustry(request.getCompany().getTypeIndustry());
        company.setUrlLogo(request.getCompany().getUrlLogo());

        Company savedCompany = companyRepository.save(company);

        UserCompany user = new UserCompany();
        user.setUserName(request.getUser().getUserName());
        user.setPassword(passwordEncoder.encode(request.getUser().getPassword()));
        user.setApiKey(request.getUser().getApiKey());
        user.setCompany(savedCompany);

        userCompanyRepository.save(user);
    }

    public UserCompany getAuthenticatedUser(HttpServletRequest request) {
        return (UserCompany) request.getAttribute("authenticatedUser");
    }

}
