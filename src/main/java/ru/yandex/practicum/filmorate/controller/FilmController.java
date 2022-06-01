package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    protected HashMap<Integer, Film> filmDeposit = new HashMap<>();
    protected LocalDate firstFilm = LocalDate.of(1895, 12, 28);
    int id = 1;

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        Film filmReturn = null;
        if (checkInputFilm(film)) {
            film.setId(id());
            filmDeposit.put(film.getId(), film);
            filmReturn = film;
            log.info("Фильм " + film.getName() + " добавлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new AssertionError("Ошибка. Введено недопустимое значение.");
        }
        return filmReturn;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        Film filmReturn = null;
        if (checkInputFilm(film)) {
            filmDeposit.put(film.getId(), film);
            filmReturn = film;
            log.info("Фильм " + film.getName() + " обновлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new AssertionError("Ошибка. Введено недопустимое значение.");
        }
        return filmReturn;
    }

    @GetMapping
    public ArrayList<Film> getAllFilm() {
        log.info("Запрошен список всех фильмов, метод getAllFilm()");
        return new ArrayList<>(filmDeposit.values());
    }

    private boolean checkInputFilm(Film film) {
        boolean check;
        check = !film.getName().isEmpty()
                && film.getDescription().length() <= 200
                && film.getReleaseDate().isAfter(firstFilm)
                && film.getDuration() > 0
                && film.getId() >= 0;
        return check;
    }

    private int id() {
        return id++;
    }
}
