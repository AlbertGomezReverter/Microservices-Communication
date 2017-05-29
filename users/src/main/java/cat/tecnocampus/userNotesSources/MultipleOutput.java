package cat.tecnocampus.userNotesSources;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by albertgomez on 31/3/17.
 */
public interface MultipleOutput {

    String DELETEUSER = "inputDeleteUser";

    String USERMODIFY = "inputUserModify";

    @Output(DELETEUSER)
    MessageChannel deleteUser();

    @Output(USERMODIFY)
    MessageChannel userModify();

}
