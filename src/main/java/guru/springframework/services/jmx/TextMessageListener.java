package guru.springframework.services.jmx;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static guru.springframework.config.JmsConfig.textMsgQueue;

@Component
public class TextMessageListener {

    @JmsListener(destination = textMsgQueue)
    public void onMessage(String msg) {
        System.out.println("#############");
        System.out.println("#############");
        System.out.println("#############");
        System.out.println("I GOT A MESSAGE");
        System.out.println(msg);
        System.out.println("#############");
        System.out.println("#############");
        System.out.println("#############");
    }
}
