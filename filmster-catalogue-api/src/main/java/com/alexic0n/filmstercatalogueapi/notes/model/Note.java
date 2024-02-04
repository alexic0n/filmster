package com.alexic0n.filmstercatalogueapi.notes.model;

import com.alexic0n.filmstercatalogueapi.AbstractCatalogueEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Note extends AbstractCatalogueEntity {

    @NotBlank
    private String filmId;
    @NotBlank
    private String note;
    @NotBlank
    private String userId;

    public Note(
            String filmId,
            String note,
            String userId
    ) {
        this.filmId = filmId;
        this.note = note;
        this.userId = userId;
    }
}
