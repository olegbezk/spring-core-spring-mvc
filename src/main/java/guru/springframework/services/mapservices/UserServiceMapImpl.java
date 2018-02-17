package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("map")
public class UserServiceMapImpl extends AbstractMapService implements UserService {

    private final EncryptionService encryptionService;

    @Autowired
    public UserServiceMapImpl(final EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Long id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {

        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public User findByUserName(final String userName) {

        Optional returnUser = super.listAll().stream()
                .filter(domainObject -> {
                    User user = (User) domainObject;
                    return user.getUsername().equalsIgnoreCase(userName);
                }).findFirst();

        if (returnUser.isPresent()) {
            return (User) returnUser.get();
        } else {
            return null;
        }
    }
}
