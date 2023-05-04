package de.hftstuttgart.vs.task06.bm;

import de.hftstuttgart.vs.task06.bm.exceptions.UserNotFound;
import de.hftstuttgart.vs.task06.bm.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    User getUser(String userName) throws UserNotFound;

}
