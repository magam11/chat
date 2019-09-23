package am.arssystems.chat.service;

import am.arssystems.chat.dto.requestWrapper.GroupWrapper;
import am.arssystems.chat.model.Group;
import am.arssystems.chat.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface GroupService {
    Group addGroup(GroupWrapper groupWrapper, User owner);

    boolean addGroupImage(Group group, MultipartFile file);
}
