package com.alexic0n.filmstercatalogueapi.films;

import com.alexic0n.filmstercatalogueapi.films.model.Film;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends ReactiveMongoRepository<Film, ObjectId> {
}
