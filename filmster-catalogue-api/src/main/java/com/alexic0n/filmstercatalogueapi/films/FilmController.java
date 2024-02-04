package com.alexic0n.filmstercatalogueapi.films;


import com.alexic0n.filmstercatalogueapi.films.model.Film;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(@Autowired FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public Mono<Film> createFilm(@RequestBody @Valid Film film) {
        return filmService.createEntity(film);
    }

    @GetMapping
    public Flux<Film> getAllFilms() {
        return filmService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Mono<Film> getFilm(@PathVariable String id) {
        return filmService.getEntityById(id);
    }

    @PatchMapping("/{id}")
    public Mono<Film> updateFilm(@PathVariable String id, @RequestBody @Valid Film film) {
        return filmService.updateEntity(id, film);
    }
}
