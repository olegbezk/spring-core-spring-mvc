package guru.springframework.services.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoginFailureEventHandler implements ApplicationListener<LoginFailureEvent> {

    @Override
    public void onApplicationEvent(final LoginFailureEvent loginFailureEvent) {

        Authentication authentication = (Authentication) loginFailureEvent.getSource();
        System.out.println("Login Failure Event for: " + authentication.getPrincipal());
    }
}
