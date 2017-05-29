package cat.tecnocampus;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by albertgomez on 31/3/17.
 */
@EnableBinding(Sink.MultipleSink.class)

public class Sink {

    @StreamListener(MultipleSink.DELETEUSER)
    public synchronized void deleteUser(String message) {
        System.out.println("******************");
        System.out.println("Han borrat un usuari");
        System.out.println("******************");
        System.out.println("L'usuari borrat es " + message);
    }
    @StreamListener(MultipleSink.USERMODIFY)
    public synchronized void userModify(String message) {
        System.out.println("******************");
        System.out.println("Han modificat un usuari");
        System.out.println("******************");
        System.out.println("L'usuari modificat es " + message);
    }
    @StreamListener(MultipleSink.NOTESMODIFY)
    public synchronized void notesModify(String message) {
        System.out.println("******************");
        System.out.println("Han modificat una nota");
        System.out.println("******************");
        System.out.println("L'id de la nota modificada es" + message);
    }
    public interface MultipleSink {
        String DELETEUSER = "outputDelete";

        String USERMODIFY= "outputUserModify";

        String NOTESMODIFY= "outputNotesModify";


        @Input(DELETEUSER)
        SubscribableChannel deleteUser();

        @Input(USERMODIFY)
        SubscribableChannel userModify();

        @Input(NOTESMODIFY)
        SubscribableChannel notesModify();
    }
}
