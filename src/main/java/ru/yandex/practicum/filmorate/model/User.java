package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    protected int id;
    @NotBlank
    protected String login;
    protected String name;
    @NotBlank
    @Email
    protected String email;
    protected LocalDate birthday;
    protected final ArrayList<Integer> friendsList = new ArrayList<>();
}