package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Film {
    protected int id;
    @NotBlank
    protected String name;
    @NotBlank
    protected String description;
    @NotNull
    protected LocalDate releaseDate;
    protected int duration;

}
