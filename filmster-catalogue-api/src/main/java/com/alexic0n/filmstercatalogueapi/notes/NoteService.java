package com.alexic0n.filmstercatalogueapi.notes;

import com.alexic0n.filmstercatalogueapi.AbstractCatalogueService;
import com.alexic0n.filmstercatalogueapi.films.FilmService;
import com.alexic0n.filmstercatalogueapi.notes.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import reactor.core.publisher.Mono;

@Service
public class NoteService extends AbstractCatalogueService<NoteRepository, Note> {

    private final FilmService filmService;

    public NoteService(
            NoteRepository repository,
            FilmService filmService
    ) {
        super(repository, "note");
        this.filmService = filmService;
    }

    @Override
    protected Mono<Note> validateInputEntity(Note entity) {
        return super.validateInputEntity(entity)
                .flatMap(note -> filmService.getEntityById(note.getFilmId())
                        .switchIfEmpty(Mono.error(
                                new ErrorResponseException(
                                        HttpStatus.BAD_REQUEST,
                                        ProblemDetail.forStatusAndDetail(
                                                HttpStatus.BAD_REQUEST,
                                                "Film with id " + note.getFilmId() + " not found"
                                        ),
                                        null
                                ))
                        )
                        .map(film -> note)
                );
    }

}
