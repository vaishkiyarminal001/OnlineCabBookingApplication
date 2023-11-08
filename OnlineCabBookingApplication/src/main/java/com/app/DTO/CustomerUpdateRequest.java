package com.app.DTO;

public class CustomerUpdateRequest {
    private String customerName;
    private String customerMobileNo;
    private String emailId;

    // Constructors, getters, and setters

    public CustomerUpdateRequest() {
    }

    public CustomerUpdateRequest(String customerName, String customerMobileNo, String emailId) {
        this.customerName = customerName;
        this.customerMobileNo = customerMobileNo;
        this.emailId = emailId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
