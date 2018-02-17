package guru.springframework.services.reposervices;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceRepoImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return userRepository.save(domainObject);
    }

    @Override
    public void delete(final Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUserName(final String userName) {
        return userRepository.findByUsername(userName);
    }
}
