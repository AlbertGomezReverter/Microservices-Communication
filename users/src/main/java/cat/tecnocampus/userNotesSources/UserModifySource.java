package cat.tecnocampus.userNotesSources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by albertgomez on 31/3/17.
 */

@Component
@EnableBinding(MultipleOutput.class)
@Qualifier(MultipleOutput.USERMODIFY)
public class UserModifySource {

    private MessageChannel loggerChannel;

    @Autowired
    @Qualifier(MultipleOutput.USERMODIFY)
    public void  setNotesChannel(MessageChannel loggerChannel) {
        this.loggerChannel = loggerChannel;
    }


    public void modifyUser(String username) {
        loggerChannel.send(MessageBuilder.withPayload(username).build());
    }
}