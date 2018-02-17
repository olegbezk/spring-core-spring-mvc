package guru.springframework.services.reposervices;

import guru.springframework.domain.User;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {

    private final UserRepository userRepository;

    private final EncryptionService encryptionService;

    private final CustomerRepository customerRepository;

    @Autowired
    public UserServiceRepoImpl(final UserRepository userRepository,
                               final EncryptionService encryptionService,
                               final CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(final Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User saveOrUpdate(final User domainObject) {

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        return userRepository.save(domainObject);
    }

    @Override
    public void delete(final Long id) {
        User user = userRepository.findOne(id);
        customerRepository.delete(user.getCustomer());
        userRepository.delete(user);
    }

    @Override
    public User findByUserName(final String userName) {
        return userRepository.findByUsername(userName);
    }
}
