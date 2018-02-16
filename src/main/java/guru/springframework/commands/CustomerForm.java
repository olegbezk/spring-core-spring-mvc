package guru.springframework.commands;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomerForm {

    private Long userId;

    private Long userVersion;

    private Long customerId;

    private Long customerVersion;

    @NotEmpty
    private String userName;

    private String passwordText;

    private String passwordTextConf;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Long getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(final Long userVersion) {
        this.userVersion = userVersion;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerVersion() {
        return customerVersion;
    }

    public void setCustomerVersion(final Long customerVersion) {
        this.customerVersion = customerVersion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(final String passwordText) {
        this.passwordText = passwordText;
    }

    public String getPasswordTextConf() {
        return passwordTextConf;
    }

    public void setPasswordTextConf(final String passwordTextConf) {
        this.passwordTextConf = passwordTextConf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
