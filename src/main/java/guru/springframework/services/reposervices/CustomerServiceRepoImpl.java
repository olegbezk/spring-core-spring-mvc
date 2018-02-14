package guru.springframework.services.reposervices;

import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpa_dao"})
public class CustomerServiceRepoImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceRepoImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
        customerRepository.delete(id);
    }
}
