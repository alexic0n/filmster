package com.alexic0n.filmstercatalogueapi.notes;


import com.alexic0n.filmstercatalogueapi.notes.model.Note;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public Flux<Note> getNotes() {
        return noteService.getAllEntities();
    }

    @GetMapping("/{id}")
    public Mono<Note> getNoteById(@PathVariable String id) {
        return noteService.getEntityById(id);
    }

    @PostMapping
    public Mono<Note> createNote(@RequestBody Note note) {
        return noteService.createEntity(note);
    }
}
