package com.alexic0n.filmstercatalogueapi.notes;

import com.alexic0n.filmstercatalogueapi.notes.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note, ObjectId>{
}
