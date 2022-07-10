package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;

@Service
public class UserService {
    protected UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addUser(User user) {
        userStorage.addUser(user);
    }

    public void updateUser(User user) {
        if (!userStorage.getUserDeposit().containsKey(user.getId())) {
            throw new UserNotFoundException("Неправильный ID пользователя");
        }
        userStorage.updateUser(user);
    }

    public ArrayList<User> getAllUser() {
        return new ArrayList<>(userStorage.getUserDeposit().values());
    }

    public User getUser(int userId) {
        if (!userStorage.getUserDeposit().containsKey(userId)) {
            throw new UserNotFoundException("Неправильный ID пользователя");
        }
        return userStorage.getUserDeposit().get(userId);
    }

    public void addFriend(int idUser, int idNewFriend) {

        if (!userStorage.getUserDeposit().containsKey(idNewFriend)) {
            throw new UserNotFoundException("Неправильный ID пользователя");
        }

        User user = userStorage.getUserDeposit().get(idUser);
        User userFriend = userStorage.getUserDeposit().get(idNewFriend);

        if (!user.getFriendsList().contains((Object) idNewFriend)
                && !userFriend.getFriendsList().contains((Object) idUser)) {
            user.getFriendsList().add(idNewFriend);
            userFriend.getFriendsList().add(idUser);
        }
    }

    public void deleteFriend(int idUser, int idUser2) {
        User user = userStorage.getUserDeposit().get(idUser);
        User user2 = userStorage.getUserDeposit().get(idUser2);

        user.getFriendsList().remove((Object) idUser2);
        user2.getFriendsList().remove((Object) idUser);
    }

    public ArrayList<User> printFriendsList(int idUser) {
        ArrayList<User> friendsList = new ArrayList<>();
        User user = userStorage.getUserDeposit().get(idUser);

        for (int i = 0; i < user.getFriendsList().size(); i++) {
            int idFriend = user.getFriendsList().get(i);
            friendsList.add(userStorage.getUserDeposit().get(idFriend));
        }
        return friendsList;
    }

    public ArrayList<User> printCommonFriendsList(int idUser, int idUser2) {
        ArrayList<User> commonFriendsList = new ArrayList<>();
        User user = userStorage.getUserDeposit().get(idUser);
        User user2 = userStorage.getUserDeposit().get(idUser2);

        if (!user2.getFriendsList().isEmpty()) {
            for (int i = 0; i < user.getFriendsList().size(); i++) {
                int idFriend = user.getFriendsList().get(i);

                if (user2.getFriendsList().contains(idFriend)) {
                    commonFriendsList.add(userStorage.getUserDeposit().get(idFriend));
                }
            }
        }

        return commonFriendsList;
    }
}
