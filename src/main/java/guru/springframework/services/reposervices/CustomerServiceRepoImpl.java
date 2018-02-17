package guru.springframework.services.reposervices;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final UserRepository userRepository;

    private final CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public CustomerServiceRepoImpl(final CustomerRepository customerRepository,
                                   final UserRepository userRepository,
                                   final CustomerFormToCustomer customerFormToCustomer) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.customerFormToCustomer = customerFormToCustomer;
    }

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(final Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(final Customer domainObject) {
        return customerRepository.save(domainObject);
    }

    @Override
    public void delete(final Long id) {

        final Customer customer = customerRepository.findOne(id);

        userRepository.delete(customer.getUser());

        customerRepository.delete(customer);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(final CustomerForm customerForm) {

        Customer newCustomer = customerFormToCustomer.convert(customerForm);

        if (newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }

        return saveOrUpdate(newCustomer);
    }
}
