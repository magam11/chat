package am.arssystems.chat.service.impl;

import am.arssystems.chat.model.User;
import am.arssystems.chat.repository.UserRepository;
import am.arssystems.chat.service.UserServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServce {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User getUserByEmailAndPass(String email, String password) {
        User result = null;
        User user = userRepository.getByEmail(email);
        if(user!=null && passwordEncoder.matches(password,user.getPassword())){
            result =user;
        }
        return result;
    }
}
