package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface FilmStorage {
    HashMap<Integer, Film> getFilmDeposit();

    void addFilm(@Valid Film film);

    void updateFilm(@Valid Film film);

    List<Film> getAllFilm();
}