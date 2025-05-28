package com.proyect.Human_Resources.dto;

public class CompanyLoginRequest {
  private String userName;
  private String password;

  public CompanyLoginRequest() {
  }

  public CompanyLoginRequest(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  // Getter y Setter para userName
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  // Getter y Setter para password
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
