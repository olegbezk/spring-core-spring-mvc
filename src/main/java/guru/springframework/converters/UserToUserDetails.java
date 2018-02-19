package guru.springframework.converters;

import guru.springframework.domain.User;
import guru.springframework.services.security.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(final User user) {

        UserDetailsImpl userDetails = new UserDetailsImpl();

        if (user != null) {
            userDetails.setUsername(user.getUsername());
            userDetails.setPassword(user.getEncryptedPassword());
            userDetails.setEnabled(user.getEnabled());
            userDetails.setAuthorities(user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRole()))
                    .collect(Collectors.toSet()));
        }

        return userDetails;
    }
}
