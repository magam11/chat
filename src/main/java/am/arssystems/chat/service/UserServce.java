package am.arssystems.chat.service;

import am.arssystems.chat.dto.responseWrapper.UserData;
import am.arssystems.chat.model.Group;
import am.arssystems.chat.model.User;

import java.util.List;
import java.util.Set;

public interface UserServce {
    User getUserByEmailAndPass(String email, String password);

    List<UserData> searchUser(User user, String text, int groupId);

    List<Group> getUserGroups(User user);
}
