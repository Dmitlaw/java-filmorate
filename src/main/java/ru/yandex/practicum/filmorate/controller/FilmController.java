package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    protected HashMap<Integer, Film> filmDeposit = new HashMap<>();
    protected LocalDate firstFilm = LocalDate.of(1895, 12, 28);
    protected int id = 1;

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        if (checkInputFilm(film)) {
            film.setId(id());
            filmDeposit.put(film.getId(), film);
            log.info("Фильм " + film.getName() + " добавлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new ValidationException("Ошибка. Введено недопустимое значение.");
        }
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        if (checkInputFilm(film)) {
            filmDeposit.put(film.getId(), film);
            log.info("Фильм " + film.getName() + " обновлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new ValidationException("Ошибка. Введено недопустимое значение.");
        }
        return film;
    }

    @GetMapping
    public ArrayList<Film> getAllFilm() {
        log.info("Запрошен список всех фильмов, метод getAllFilm()");
        return new ArrayList<>(filmDeposit.values());
    }

    private boolean checkInputFilm(Film film) {
        return !film.getName().isEmpty()
                && film.getDescription().length() <= 200
                && film.getReleaseDate().isAfter(firstFilm)
                && film.getDuration() > 0
                && film.getId() >= 0;
    }

    private int id() {
        return id++;
    }
}
