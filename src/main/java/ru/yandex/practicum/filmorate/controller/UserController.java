package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    protected HashMap<Integer, User> userDeposit = new HashMap<>();
    int id = 1;

    @PostMapping
    public User addUser(@RequestBody User user) {
        User userReturn = null;
        String name;
        if (checkInputUser(user)) {
            name = user.getName().isEmpty() ? user.getLogin() : user.getName();
            user.setName(name);
            user.setId(id());
            userDeposit.put(user.getId(), user);
            userReturn = user;
            log.info("Пользователь " + user.getName() + " добавлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new AssertionError("Ошибка. Введено недопустимое значение.");
        }
        return userReturn;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        User userReturn = null;
        if (checkInputUser(user)) {
            userDeposit.put(user.getId(), user);
            userReturn = user;
            log.info("Пользователь " + user.getName() + " обновлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new AssertionError("Ошибка. Введено недопустимое значение.");
        }
        return userReturn;
    }

    @GetMapping
    public ArrayList<User> getAllUser() {
        log.info("Запрошен список всех пользователей, метод getAllUser()");
        return new ArrayList<>(userDeposit.values());
    }

    private boolean checkInputUser(User user) {
        boolean check;
        LocalDate dateNow = LocalDate.now();
        check = !user.getEmail().isEmpty()
                && user.getEmail().contains("@")
                && !user.getLogin().isEmpty()
                && !user.getLogin().contains(" ")
                && user.getBirthday().isBefore(dateNow)
                && user.getId() >= 0;
        return check;
    }

    private int id() {
        return id++;
    }
}
