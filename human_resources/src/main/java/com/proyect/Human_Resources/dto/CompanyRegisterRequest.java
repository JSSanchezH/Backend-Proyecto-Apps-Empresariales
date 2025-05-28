package com.proyect.Human_Resources.dto;

public class CompanyRegisterRequest {
  private CompanyRequest company;
  private UserCompanyRequest user;

  public CompanyRequest getCompany() {
    return company;
  }

  public void setCompany(CompanyRequest company) {
    this.company = company;
  }

  public UserCompanyRequest getUser() {
    return user;
  }

  public void setUser(UserCompanyRequest user) {
    this.user = user;
  }

  public static class CompanyRequest {
    private String name;
    private long nit;
    private String address;
    private String email;
    private String typeIndustry;
    private String urlLogo;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public long getNit() {
      return nit;
    }

    public void setNit(long nit) {
      this.nit = nit;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getTypeIndustry() {
      return typeIndustry;
    }

    public void setTypeIndustry(String typeIndustry) {
      this.typeIndustry = typeIndustry;
    }

    public String getUrlLogo() {
      return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
      this.urlLogo = urlLogo;
    }
  }

  public static class UserCompanyRequest {
    private String userName;
    private String password;
    private String apiKey;

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getApiKey() {
      return apiKey;
    }

    public void setApiKey(String apiKey) {
      this.apiKey = apiKey;
    }
  }
}
