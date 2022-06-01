package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.xml.datatype.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Film {
    int id;
    String name;
    String description;
    LocalDate releaseDate;
    int duration;

}
