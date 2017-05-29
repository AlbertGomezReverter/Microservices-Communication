package cat.tecnocampus.userNotesSources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by josep on 2/3/17.
 */
@Component
@EnableBinding(Source.class)
@Qualifier(MultipleOutput.DELETEUSER)
public class UserNotesSources {

    private MessageChannel notesChannel;

    @Autowired @Qualifier(MultipleOutput.DELETEUSER)
    public void  setNotesChannel(MessageChannel notesChannel) {
        this.notesChannel = notesChannel;
    }


    public void deleteUserNotes(String username) {
        notesChannel.send(MessageBuilder.withPayload(username).build());
    }
}

