package com.alexic0n.filmstercatalogueapi.films;

import com.alexic0n.filmstercatalogueapi.AbstractCatalogueService;
import com.alexic0n.filmstercatalogueapi.films.model.Film;
import org.springframework.stereotype.Service;

@Service
public class FilmService extends AbstractCatalogueService<FilmRepository, Film> {

    public FilmService(FilmRepository repository) {
        super(repository, "film");
    }
}
