package guru.springframework.services.queue;

import guru.springframework.services.SendTextMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class SendTextMessageServiceImpl implements SendTextMessageService {

    @Qualifier("textMessageQueue")
    private final Queue queue;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public SendTextMessageServiceImpl(final Queue queue, final JmsTemplate jmsTemplate) {
        this.queue = queue;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendTextMessage(final String msg) {
        this.jmsTemplate.convertAndSend(this.queue, msg);
    }
}
