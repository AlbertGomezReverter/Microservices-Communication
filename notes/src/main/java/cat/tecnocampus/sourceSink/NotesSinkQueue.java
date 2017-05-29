package cat.tecnocampus.sourceSink;

import cat.tecnocampus.useCases.NoteUseCases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by josep on 2/3/17.
 */
@EnableBinding(Sink.class)
public class NotesSinkQueue {

    private NoteUseCases noteUseCases;

    @Autowired
    public void setNoteUseCases(NoteUseCases noteUseCases) {
        this.noteUseCases = noteUseCases;
    }


    @StreamListener(Sink.INPUT)
    public void deleteNotesSink(String message) {
        noteUseCases.deleteUserNotes(message);
    }
}


