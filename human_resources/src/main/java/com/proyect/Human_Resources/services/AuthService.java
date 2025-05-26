package com.proyect.Human_Resources.services;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyect.Human_Resources.Execeptions.UserAlreadyExisteException;
import com.proyect.Human_Resources.Repositories.ICompanyRepository;
import com.proyect.Human_Resources.Repositories.IUserCompanyRepository;
import com.proyect.Human_Resources.dto.CompanyRegisterRequest;
import com.proyect.Human_Resources.models.Company;
import com.proyect.Human_Resources.models.UserCompany;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthService {
    private final ICompanyRepository companyRepository;
    private final IUserCompanyRepository userCompanyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserCompanyService userCompanyService;

    public AuthService(ICompanyRepository companyRepository,
            IUserCompanyRepository userCompanyRepository,
            PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.userCompanyRepository = userCompanyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerCompanyWithUser(CompanyRegisterRequest request) {

        Optional<UserCompany> existingUser = userCompanyRepository
                .findByUserName(request.getUser().getUserName());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExisteException("User already exists with username: "
                    + request.getUser().getUserName());
        }


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
        user.setApiKey(userCompanyService.generateApiKey());
        user.setCompany(savedCompany);

        userCompanyRepository.save(user);

        return "Company and user registered successfully";
    }

    public UserCompany getAuthenticatedUser(HttpServletRequest request) {
        return (UserCompany) request.getAttribute("authenticatedUser");
    }

}
