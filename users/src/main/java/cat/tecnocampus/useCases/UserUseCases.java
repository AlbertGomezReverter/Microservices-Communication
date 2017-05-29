package cat.tecnocampus.useCases;

import cat.tecnocampus.domain.UserLab;
import cat.tecnocampus.userNotesSources.UserModifySource;
import cat.tecnocampus.userNotesSources.UserNotesSources;
import cat.tecnocampus.repositories.UserLabRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jordi on 09/02/2017.
 */

@Service
public class UserUseCases {

    private final UserLabRepository userLabRepository;
    private final UserNotesSources messageSource;
    private final UserModifySource userModifySource;

    public UserUseCases(UserLabRepository userLabRepository, UserNotesSources messageSource, UserModifySource userModifySource) {
        this.userLabRepository = userLabRepository;
        this.messageSource = messageSource;
        this.userModifySource=userModifySource;
    }

    public UserLab createUser(String username, String name, String secondName, String email) {
        UserLab userLab = new UserLab(username, name, secondName, email);
        registerUser(userLab);
        return userLab;
    }

    @Transactional
    public int registerUser(UserLab userLab) {
        try {
            int res= userLabRepository.save(userLab);
            userModifySource.modifyUser(userLab.getUsername());
            return res;
        } catch (DuplicateKeyException e) {
            //throw new UserLabUsernameAlreadyExistsException(userLab.getUsername()); faltaría la exception
            throw e;
        }
    }

    @Transactional
    public void saveUser(UserLab user) {
        userLabRepository.save(user);
        userModifySource.modifyUser(user.getUsername());
    }

    public UserLab getUser(String userName) {
        try {
            return userLabRepository.findOne(userName);
        } catch (EmptyResultDataAccessException e) {
            //throw new UserLabNotFoundException("UserLab " + userName + " not found"); // faltarían las excepciones
            throw e;
        }
    }

    public List<UserLab> getUsers() {
        return userLabRepository.findAllLazy();
    }

    public int deleteUser(String username) {
        int res = userLabRepository.delete(username);
        messageSource.deleteUserNotes(username);

        return res;
    }

}
