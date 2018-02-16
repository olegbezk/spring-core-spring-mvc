package guru.springframework.commands;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerForm {

    private Long userId;

    private Long userVersion;

    private Long customerId;

    private Long customerVersion;

    @NotEmpty
    @Size(min = 2, max = 75)
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String passwordText;

    @NotEmpty
    @Size(min = 6, max = 20)
    private String passwordTextConf;

    @NotEmpty
    @Size(min = 2, max = 75)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 75)
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})", message = "Should be 10 digits long.")
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
