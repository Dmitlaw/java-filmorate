package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Test;
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
        AssertionError errorNameEmpty = assertThrows(AssertionError.class,
                () -> filmController.addFilm(filmNameEmpty));
        AssertionError errorDescriptionOver200 = assertThrows(AssertionError.class,
                () -> filmController.addFilm(filmDescriptionOver200));
        AssertionError errorRealaseDate = assertThrows(AssertionError.class,
                () -> filmController.addFilm(filmRealaseDateBefore1895));
        AssertionError errorDurationZero = assertThrows(AssertionError.class,
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
        AssertionError errorNameEmpty = assertThrows(AssertionError.class,
                () -> filmController.updateFilm(filmNameEmpty));
        AssertionError errorDescriptionOver200 = assertThrows(AssertionError.class,
                () -> filmController.updateFilm(filmDescriptionOver200));
        AssertionError errorReleaseDate = assertThrows(AssertionError.class,
                () -> filmController.updateFilm(filmReleaseDateBefore1895));
        AssertionError errorDurationZero = assertThrows(AssertionError.class,
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