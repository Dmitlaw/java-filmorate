package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {
    protected HashMap<Integer, Film> filmDeposit = new HashMap<>();
    protected LocalDate firstFilm = LocalDate.of(1895, 12, 28);
    protected int id = 1;

    @Override
    public HashMap<Integer, Film> getFilmDeposit() {
        return filmDeposit;
    }

    @Override
    public void addFilm(@Valid Film film) {
        if (checkInputFilm(film)) {
            film.setId(id());
            filmDeposit.put(film.getId(), film);
            log.info("Фильм " + film.getName() + " добавлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new ValidationException("Ошибка. Введено недопустимое значение.");
        }
    }

    @Override
    public void updateFilm(@Valid Film film) {
        if (checkInputFilm(film)) {
            filmDeposit.put(film.getId(), film);
            log.info("Фильм " + film.getName() + " обновлен.");
        } else {
            log.warn("Ошибка. Введено недопустимое значение.");
            throw new ValidationException("Ошибка. Введено недопустимое значение.");
        }
    }

    public List<Film> getAllFilm() {
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