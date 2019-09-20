package am.arssystems.chat.service.impl;

import am.arssystems.chat.dto.responseWrapper.UserData;
import am.arssystems.chat.model.GroupUsersId;
import am.arssystems.chat.model.User;
import am.arssystems.chat.repository.GroupUserRepository;
import am.arssystems.chat.repository.UserRepository;
import am.arssystems.chat.service.UserServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserServce {

    private UserRepository userRepository;
    private GroupUserRepository groupUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           GroupUserRepository groupUserRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.groupUserRepository = groupUserRepository;
    }


    @Override
    public User getUserByEmailAndPass(String email, String password) {
        User result = null;
        User user = userRepository.getUserByEmail(email);
        if(user!=null && passwordEncoder.matches(password,user.getPassword())){
            result =user;
        }
        return result;
    }

    @Override
    public List<UserData> searchUser(User user, String text, int groupId) {
        List<UserData> result = null;
        Page<UserData> data = userRepository.findAllByFirstNameOrEmailContainingAndIdNotIn(text, Collections.singletonList(user.getId()), PageRequest.of(0, 25));
        result = data.getContent();
        if(groupId!=0){ // when there is the group,
            for (UserData userData : result) {
                userData.setInGroup(groupUserRepository.existsById(GroupUsersId.builder()
                        .groupId(groupId)
                        .userId(userData.getId())
                        .build()));
            }
        }
        return result;
    }
}
