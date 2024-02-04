package com.alexic0n.filmstercatalogueapi;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractCatalogueService<S extends ReactiveMongoRepository<T, ObjectId>, T extends AbstractCatalogueEntity> {

        protected final S repository;

        protected final String entityName;

        public AbstractCatalogueService(S repository, String entityName) {
            this.repository = repository;
            this.entityName = entityName;
        }

        protected Mono<T> validateInputEntity(T entity) {
            return Mono.just(entity);
        }

        public Mono<T> createEntity(T entity) {
            return validateInputEntity(entity)
                    .flatMap(repository::save);
        }

        public Mono<T> updateEntity(String id, T entity) {
            validateInputEntity(entity);
            ObjectId objectId = getObjectId(id);
            return repository
                    .findById(objectId)
                    .flatMap(existingEntity -> {
                        entity.setId(existingEntity.getId());
                        return repository.save(entity);
                    })
                    .switchIfEmpty(
                            Mono.error(notFoundException(id))
                    );
        }

        public Flux<T> getAllEntities() {
            return repository.findAll();
        }

        public Mono<T> getEntityById(String id)  {
            ObjectId objectId = getObjectId(id);
            return repository
                    .findById(objectId)
                    .switchIfEmpty(
                            Mono.error(notFoundException(id))
                    );
        }

        protected ObjectId getObjectId(String id) {
            return new ObjectId(id);
        }

        protected ErrorResponseException notFoundException(String id) {
            return new ErrorResponseException(
                    HttpStatus.NOT_FOUND,
                    ProblemDetail.forStatusAndDetail(
                            HttpStatus.NOT_FOUND,
                            String.format("%s with id %s not found", entityName, id)
                    ),
                    null
            );
        }
}
