package cat.tecnocampus.sourceSink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by albertgomez on 31/3/17.
 */
@Component
@EnableBinding(Source.class)
@Qualifier("output")
public class NotesSource {

    private MessageChannel notesChannel;

    @Autowired @Output("output") @Qualifier("output")
    public void  setNotesChannel(MessageChannel notesChannel) {
        this.notesChannel = notesChannel;
    }


    public void notesModify(int id) {
        notesChannel.send(MessageBuilder.withPayload(id).build());
    }
}
