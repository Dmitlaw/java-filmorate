package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void addUser() {
        UserController userController = new UserController();
        User user = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("Dmitry")
                .build();
        User userWrongEmail = User.builder()
                .id(2)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("gmail.com")
                .name("Дмитрий")
                .login("Dmitry")
                .build();
        User userLoginEmpty = User.builder()
                .id(3)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("")
                .build();
        User userLoginSpace = User.builder()
                .id(4)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login(" ")
                .build();
        User userBirthFuture = User.builder()
                .id(5)
                .birthday(LocalDate.parse("2023-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("Дмитрий")
                .build();

        userController.addUser(user);
        ValidationException errorEmail = assertThrows(ValidationException.class,
                () -> userController.addUser(userWrongEmail));
        ValidationException errorLoginEmpty = assertThrows(ValidationException.class,
                () -> userController.addUser(userLoginEmpty));
        ValidationException errorLoginSpace = assertThrows(ValidationException.class,
                () -> userController.addUser(userLoginSpace));
        ValidationException errorBirthFuture = assertThrows(ValidationException.class,
                () -> userController.addUser(userBirthFuture));

        assertEquals(userController.userDeposit.get(1), user, "Ошибка. Пользователь не добавлен.");
        assertEquals(errorEmail.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при неправильном email.");
        assertEquals(errorLoginEmpty.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при пустом поле Логин.");
        assertEquals(errorLoginSpace.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при наличии в поле Логин пробела.");
        assertEquals(errorBirthFuture.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при указании дня рождения в будущем.");
    }

    @Test
    void updateUser() {
        UserController userController = new UserController();
        User user = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("Dmitry")
                .build();
        User userUp = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1992-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("ДмитрийUp")
                .login("DmitryUp")
                .build();
        User userWrongEmail = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("gmail.com")
                .name("Дмитрий")
                .login("Dmitry")
                .build();
        User userLoginEmpty = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("")
                .build();
        User userLoginSpace = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login(" ")
                .build();
        User userBirthFuture = User.builder()
                .id(1)
                .birthday(LocalDate.parse("2023-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login(" ")
                .build();

        userController.updateUser(userUp);
        ValidationException errorEmail = assertThrows(ValidationException.class,
                () -> userController.updateUser(userWrongEmail));
        ValidationException errorLoginEmpty = assertThrows(ValidationException.class,
                () -> userController.updateUser(userLoginEmpty));
        ValidationException errorLoginSpace = assertThrows(ValidationException.class,
                () -> userController.updateUser(userLoginSpace));
        ValidationException errorBirthFuture = assertThrows(ValidationException.class,
                () -> userController.updateUser(userBirthFuture));

        assertEquals(userController.userDeposit.get(1), userUp, "Ошибка. Пользователь не обновлен");
        assertEquals(errorEmail.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при неправильном email.");
        assertEquals(errorLoginEmpty.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при неправильном email.");
        assertEquals(errorLoginSpace.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при наличии в поле Логин пробела.");
        assertEquals(errorBirthFuture.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Метод работает при указании дня рождения в будущем.");
    }

    @Test
    void getAllUser() {
        UserController userController = new UserController();
        User user = User.builder()
                .id(1)
                .birthday(LocalDate.parse("1990-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("Dmitry")
                .build();
        User user2 = User.builder()
                .id(2)
                .birthday(LocalDate.parse("1992-07-06"))
                .email("d.e.pechagin@gmail.com")
                .name("Дмитрий")
                .login("Dmitry")
                .build();

        userController.addUser(user);
        userController.addUser(user2);

        assertEquals(userController.userDeposit.size(),
                userController.getAllUser().size(), "Ошибка. Все пользователи не выведены");
    }
}