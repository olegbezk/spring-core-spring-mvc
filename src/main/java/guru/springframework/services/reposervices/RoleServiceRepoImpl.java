package guru.springframework.services.reposervices;

import guru.springframework.domain.security.Role;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpa_dao"})
public class RoleServiceRepoImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceRepoImpl(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(final Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(final Role domainObject) {
        return roleRepository.save(domainObject);
    }

    @Override
    public void delete(final Long id) {
        roleRepository.delete(id);
    }
}
