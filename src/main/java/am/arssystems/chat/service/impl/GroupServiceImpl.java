package am.arssystems.chat.service.impl;

import am.arssystems.chat.dto.requestWrapper.GroupWrapper;
import am.arssystems.chat.model.Group;
import am.arssystems.chat.model.GroupUser;
import am.arssystems.chat.model.GroupUsersId;
import am.arssystems.chat.model.User;
import am.arssystems.chat.repository.GroupRepository;
import am.arssystems.chat.repository.GroupUserRepository;
import am.arssystems.chat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;
    private GroupUserRepository groupUserRepository;

    @Value("${image.folder.group}")
    private String groupFolderPath;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupUserRepository groupUserRepository) {
        this.groupRepository = groupRepository;
        this.groupUserRepository = groupUserRepository;
    }


    @Override
    public Group addGroup(GroupWrapper groupWrapper, User groupOwner) {
        Group savedUser = groupRepository.save(Group.builder()
                .name(groupWrapper.getGroupName())
                .owner(groupOwner)
                .build());
        groupUserRepository.save(GroupUser.builder()
                .id(GroupUsersId.builder()
                        .userId(groupOwner.getId())
                        .groupId(savedUser.getId())
                        .build())
                .group(savedUser)
                .user(groupOwner)
                .build());
        return savedUser;


    }

    @Override
    public boolean addGroupImage(Group group, MultipartFile file) {
        boolean isAdded = true;
        try {
            File fil = new File(groupFolderPath + group.getImagePath());
            if (fil.exists()) {
                fil.delete();
            }
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            group.setImagePath(filename);
            groupRepository.save(group);
            File newImage = new File(groupFolderPath + filename);
            file.transferTo(newImage);
        } catch (IOException e) {
            isAdded = false;
            e.printStackTrace();
        }
        return isAdded;
    }
}
