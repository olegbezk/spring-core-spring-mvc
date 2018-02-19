package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureEventHandler implements ApplicationListener<LoginFailureEvent> {

    private final UserService userService;

    @Autowired
    public LoginFailureEventHandler(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(final LoginFailureEvent loginFailureEvent) {

        Authentication authentication = (Authentication) loginFailureEvent.getSource();
        System.out.println("Login Failure Event for: " + authentication.getPrincipal());

        updateUserAccount(authentication);
    }

    private void updateUserAccount(Authentication authentication) {
        final User user = userService.findByUserName((String) authentication.getPrincipal());

        if (user != null) { // user found
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);

            System.out.println("Valid username, updating failed attempts");

            if (user.getFailedLoginAttempts() > 5) {
                user.setEnabled(false);
                System.out.println("### LOCKING USER ACCOUNT!");
            }

            userService.saveOrUpdate(user);

            if (!user.getEnabled()) {
                System.out.println("User account rich limit of attempts to login and now disabled");
            }
        }
    }
}
