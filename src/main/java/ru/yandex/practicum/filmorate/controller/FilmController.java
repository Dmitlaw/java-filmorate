package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    protected FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        filmService.addFilm(film);
        log.info("Фильм " + film.getName() + " добавлен.");
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        filmService.updateFilm(film);
        log.info("Фильм " + film.getName() + " обновлен.");
        return film;
    }

    @GetMapping
    public ArrayList<Film> getAllFilm() {
        log.info("Запроше список фильмов.");
        return new ArrayList<>(filmService.getAllFilm());
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable("id") int id) {
        log.info("Запрошен фильм с ID № " + id);
        return filmService.getFilm(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void like(@PathVariable("id") int id,
                     @PathVariable("userId") int userId) {
        filmService.addLike(id, userId);
        log.info("Фильму с ID № " + id + " добавил лайк пользователь с ID № " + userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void unlike(@PathVariable("id") int id,
                       @PathVariable("userId") int userId) {
        filmService.deleteLike(id, userId);
        log.info("Фильму с ID № " + id + " удалил лайк пользователь с ID № " + userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopular(@RequestParam(value = "count", defaultValue = "10") int count) {
        log.info("Запрошен список популярных фильмов");
        return filmService.getPopularFilms(count);
    }

}
