package am.arssystems.chat.controller;

import am.arssystems.chat.dto.requestWrapper.GroupWrapper;
import am.arssystems.chat.model.Group;
import am.arssystems.chat.model.view.Views;
import am.arssystems.chat.security.CurrentUser;
import am.arssystems.chat.service.GroupService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

@RestController
@RequestMapping("group")
public class GroupController {


    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @PostMapping("/addGroup")
    @PreAuthorize("hasAuthority('user')")
    @JsonView(Views.Id.class)
    public ResponseEntity addGroup(@AuthenticationPrincipal CurrentUser currentUser,
                                   @RequestBody GroupWrapper groupWrapper) {
        Group savedGroup = groupService.addGroup(groupWrapper, currentUser.getUser());
        return ResponseEntity.ok(savedGroup);
    }


    @PostMapping("/addImage/{groupId}")
    public ResponseEntity addGroupImage(@PathVariable("groupId") @NotNull Group group,
                                        @RequestParam(name = "picture") MultipartFile file) {
        boolean isAdded = false;
        if (group != null) {
            isAdded = groupService.addGroupImage(group, file);
        }
        return ResponseEntity.ok(isAdded);
    }

}
