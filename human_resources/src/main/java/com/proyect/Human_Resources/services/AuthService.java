package com.proyect.Human_Resources.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyect.Human_Resources.Exceptions.UserAlreadyExistException;
import com.proyect.Human_Resources.Repositories.ICompanyRepository;
import com.proyect.Human_Resources.Repositories.IUserCompanyRepository;
import com.proyect.Human_Resources.dto.CompanyRegisterRequest;
import com.proyect.Human_Resources.dto.LoginResponse;
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

public LoginResponse login(String userName, String password) {
    Optional<UserCompany> userOptional = userCompanyRepository.findByUserName(userName);

    if (userOptional.isEmpty()) {
        throw new RuntimeException("User not found");
    }

    UserCompany user = userOptional.get();

    if (!passwordEncoder.matches(password, user.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    return new LoginResponse(user.getApiKey(), user.getCompany().getId());
}
    

    public String registerCompanyWithUser(CompanyRegisterRequest request) {

        Optional<UserCompany> existingUser = userCompanyRepository
                .findByUserName(request.getUser().getUserName());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exists with username: "
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

    public void setUserCompanyService(UserCompanyService userCompanyService) {
        this.userCompanyService = userCompanyService;
    }

}
