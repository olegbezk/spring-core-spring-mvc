package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessEventHandler implements ApplicationListener<LoginSuccessEvent> {

    private final UserService userService;

    @Autowired
    public LoginSuccessEventHandler(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(final LoginSuccessEvent event) {
        Authentication authentication = (Authentication) event.getSource();

        final UserDetails principal = (UserDetails) authentication.getPrincipal();

        System.out.println("Login for: " + principal.getUsername());

        updateUserAccount(principal);
    }

    private void updateUserAccount(UserDetails userDetails) {

        final User user = userService.findByUserName(userDetails.getUsername());

        if (user != null) { // user found

            if (user.getFailedLoginAttempts() > 0) {
                System.out.println("Good logging, resetting failed attempts");
            }

            user.setFailedLoginAttempts(0);

            userService.saveOrUpdate(user);
        }
    }
}
