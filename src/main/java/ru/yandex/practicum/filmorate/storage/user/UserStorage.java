package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

public interface UserStorage {
    HashMap<Integer, User> getUserDeposit();

    void addUser(User user);

    User updateUser(User user);
}
