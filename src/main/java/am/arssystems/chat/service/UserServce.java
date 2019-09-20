package am.arssystems.chat.service;

import am.arssystems.chat.model.User;

public interface UserServce {
    User getUserByEmailAndPass(String email, String password);
}
