package ru.yandex.practicum.filmorate.service;

import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FilmService {
    protected FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void addFilm(Film film) {
        filmStorage.addFilm(film);
    }

    public void updateFilm(Film film) {
        if (!filmStorage.getFilmDeposit().containsKey(film.getId())) {
            throw new UserNotFoundException("Неправильный ID фильма");
        }
        filmStorage.updateFilm(film);
    }

    public List<Film> getAllFilm() {
        return filmStorage.getAllFilm();
    }

    public Film getFilm(int filmId) {
        if (!filmStorage.getFilmDeposit().containsKey(filmId)) {
            throw new UserNotFoundException("Неправильный ID фильма");
        }

        return filmStorage.getFilmDeposit().get(filmId);
    }

    public void addLike(int filmId, int userId) {
        Film film = filmStorage.getFilmDeposit().get(filmId);

        if (!film.getLikesList().contains(userId)) {
            film.getLikesList().add(userId);
        }
    }

    public void deleteLike(int filmId, int userId) {
        Film film = filmStorage.getFilmDeposit().get(filmId);

        if (!(userId >= 0)) {
            throw new UserNotFoundException("Неправильный ID пользователя");
        }
            film.getLikesList().remove((Object) userId);
    }

    public List<Film> getPopularFilms(int count) {

        List<Film> popularFilmsList = filmStorage.getAllFilm();
        popularFilmsList.sort((f1, f2) -> f2.getLikesList().size() - f1.getLikesList().size());
        if (popularFilmsList.size() < count) {
            return popularFilmsList;
        } else {
            return popularFilmsList.subList(0, count);
        }
    }
}
