package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    int id;
    String login;
    String name;
    String email;
    LocalDate birthday;

}