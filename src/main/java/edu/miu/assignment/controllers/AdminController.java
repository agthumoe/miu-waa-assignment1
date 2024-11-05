package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.UserDto;
import edu.miu.assignment.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    @GetMapping
    public List<UserDto> getAllUsers(
            @RequestParam(name = "posts:gt", required = false, defaultValue = "0") int size,
            @RequestParam(name = "posts:title", required = false) String postTitle) {
        if (size > 0) {
            return this.userService.findAllUsersHavingPostGreaterThan(size);
        }
        if (postTitle != null && !postTitle.isEmpty()) {
            return this.userService.findAllUsersThatMadePostsWithinGivenTitle(postTitle);
        }
        return this.userService.findAll();
    }
}
