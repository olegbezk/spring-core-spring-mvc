package guru.springframework.services.security;

import org.springframework.context.ApplicationEvent;

public class LoginSuccessEvent extends ApplicationEvent {

    /**
     * Create new application event
     *
     * @param source the object on which initially event will occurred (never {@code null})
     */
    public LoginSuccessEvent(final Object source) {
        super(source);
    }
}
