package guru.springframework.commands.validators;

import guru.springframework.commands.CustomerForm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerFormValidatorTest {

    private Validator validator;

    private CustomerForm customerForm;

    private Errors errors;

    @Before
    public void setup() {
        validator = new CustomerFormValidator();
        customerForm = new CustomerForm();
        errors = new BeanPropertyBindingResult(customerForm, "customerForm");
    }

    @Test
    public void testNoErrors() {
        customerForm.setPasswordText("password");
        customerForm.setPasswordTextConf("password");

        validator.validate(customerForm, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void testHasErrors() {
        customerForm.setPasswordText("password");
        customerForm.setPasswordTextConf("asdf");

        validator.validate(customerForm, errors);

        assertTrue(errors.hasErrors());
    }
}