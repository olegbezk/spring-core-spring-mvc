package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Qualifier("userToUserDetails")
    private final Converter<User, UserDetails> userUserDetailsConverter;

    @Autowired
    public SpringSecUserDetailsServiceImpl(final UserService userService,
                                           final Converter<User, UserDetails> userUserDetailsConverter) {
        this.userService = userService;
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(userService.findByUserName(username));
    }
}
