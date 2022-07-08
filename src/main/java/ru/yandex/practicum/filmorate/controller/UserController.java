package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    protected UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @GetMapping
    public ArrayList<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable("id") int id,
                            @PathVariable("friendId") int friendId) {
        userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriends(@PathVariable("id") int id,
                                 @PathVariable("friendId") int friendId) {
        userService.deleteFriend(id, friendId);
    }

    @GetMapping("/{id}/friends")
    public ArrayList<User> getFriends(@PathVariable("id") int id) {
        return userService.printFriendsList(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getFriends(@PathVariable("id") int id,
                                 @PathVariable("otherId") int otherId) {
        return userService.printCommonFriendsList(id, otherId);
    }

}
