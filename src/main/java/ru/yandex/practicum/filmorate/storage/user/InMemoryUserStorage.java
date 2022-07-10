package ru.yandex.practicum.filmorate.storage.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    protected HashMap<Integer, User> userDeposit = new HashMap<>();
    protected int id = 1;

    @Override
    public HashMap<Integer, User> getUserDeposit() {
        return userDeposit;
    }

    @Override
    public void addUser(@Valid User user) {
        String name;
        if (checkInputUser(user)) {
            name = user.getName().isEmpty() ? user.getLogin() : user.getName();
            user.setName(name);
            user.setId(id());
            userDeposit.put(user.getId(), user);
            log.info("Пользователь " + user.getName() + " добавлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new ValidationException("Ошибка. Введено недопустимое значение.");
        }
    }

    @Override
    public User updateUser(@Valid User user) {
        if (checkInputUser(user)) {
            userDeposit.put(user.getId(), user);
            log.info("Пользователь " + user.getName() + " обновлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new ValidationException("Ошибка. Введено недопустимое значение.");
        }
        return user;
    }

    private boolean checkInputUser(User user) {
        LocalDate dateNow = LocalDate.now();
        return !user.getEmail().isEmpty()
                && user.getEmail().contains("@")
                && !user.getLogin().isEmpty()
                && !user.getLogin().contains(" ")
                && user.getBirthday().isBefore(dateNow)
                && user.getId() >= 0;
    }

    private int id() {
        return id++;
    }
}
