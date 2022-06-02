package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    @Test
    void addFilm() {
        FilmController filmController = new FilmController();
        Film film = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmNameEmpty = Film.builder()
                .id(1)
                .name("")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmDescriptionOver200 = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Однажды Василий получает производственную травму и уезжает по путёвке на море на лечение." +
                        "На курорте он знакомится с Раисой Захаровной (Людмила Гурченко)," +
                        "сотрудницей отдела кадров леспромхоза, в котором работает Василий. Эта городская жительница, " +
                        "дама взбалмошная и экзальтированная, завораживает Василия своими удивительными рассказами " +
                        "об экстрасенсах, телекинезе, астральных телах и гуманоидах. У них происходит курортный роман.")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmRealaseDateBefore1895 = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1894-01-07"))
                .build();
        Film filmDurationZero = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(0)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();

        filmController.addFilm(film);
        ValidationException errorNameEmpty = assertThrows(ValidationException.class,
                () -> filmController.addFilm(filmNameEmpty));
        ValidationException errorDescriptionOver200 = assertThrows(ValidationException.class,
                () -> filmController.addFilm(filmDescriptionOver200));
        ValidationException errorRealaseDate = assertThrows(ValidationException.class,
                () -> filmController.addFilm(filmRealaseDateBefore1895));
        ValidationException errorDurationZero = assertThrows(ValidationException.class,
                () -> filmController.addFilm(filmDurationZero));

        assertEquals(filmController.filmDeposit.get(1), film, "Ошибка. Фильм не добавлен.");
        assertEquals(errorNameEmpty.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Название фильма не заполнено.");
        assertEquals(errorDescriptionOver200.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Описание фильма более чем 200 символов.");
        assertEquals(errorRealaseDate.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Дата релиза фильма ранее 28.12.1895");
        assertEquals(errorDurationZero.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Длинна фильма менее 1й минуты или отрицательная.");
    }

    @Test
    void updateFilm() {
        FilmController filmController = new FilmController();
        Film film = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmUp = Film.builder()
                .id(1)
                .name("Любовь и голуби. Режиссерская версия.")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmNameEmpty = Film.builder()
                .id(1)
                .name("")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmDescriptionOver200 = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Однажды Василий получает производственную травму и уезжает по путёвке на море на лечение." +
                        "На курорте он знакомится с Раисой Захаровной (Людмила Гурченко)," +
                        "сотрудницей отдела кадров леспромхоза, в котором работает Василий. Эта городская жительница, " +
                        "дама взбалмошная и экзальтированная, завораживает Василия своими удивительными рассказами " +
                        "об экстрасенсах, телекинезе, астральных телах и гуманоидах. У них происходит курортный роман.")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film filmReleaseDateBefore1895 = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1894-01-07"))
                .build();
        Film filmDurationZero = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(0)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();

        filmController.updateFilm(filmUp);
        ValidationException errorNameEmpty = assertThrows(ValidationException.class,
                () -> filmController.updateFilm(filmNameEmpty));
        ValidationException errorDescriptionOver200 = assertThrows(ValidationException.class,
                () -> filmController.updateFilm(filmDescriptionOver200));
        ValidationException errorReleaseDate = assertThrows(ValidationException.class,
                () -> filmController.updateFilm(filmReleaseDateBefore1895));
        ValidationException errorDurationZero = assertThrows(ValidationException.class,
                () -> filmController.updateFilm(filmDurationZero));

        assertEquals(filmController.filmDeposit.get(1), filmUp, "Ошибка. Фильм не обновлен.");
        assertEquals(errorNameEmpty.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Название фильма не заполнено.");
        assertEquals(errorDescriptionOver200.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Описание фильма более чем 200 символов.");
        assertEquals(errorReleaseDate.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Дата релиза фильма ранее 28.12.1895");
        assertEquals(errorDurationZero.getMessage(), "Ошибка. Введено недопустимое значение.",
                "Ошибка. Длинна фильма менее 1й минуты или отрицательная.");
    }

    @Test
    void getAllFilm() {
        FilmController filmController = new FilmController();
        Film film = Film.builder()
                .id(1)
                .name("Любовь и голуби")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();
        Film film2 = Film.builder()
                .id(2)
                .name("Любовь и голуби. Режиссерская версия.")
                .description("Роковая женщина Раиса Захаровна")
                .duration(107)
                .releaseDate(LocalDate.parse("1985-01-07"))
                .build();

        filmController.addFilm(film);
        filmController.addFilm(film2);

        assertEquals(filmController.filmDeposit.size(),
                filmController.getAllFilm().size(), "Ошибка. Все фильмы не выведены");
    }


}