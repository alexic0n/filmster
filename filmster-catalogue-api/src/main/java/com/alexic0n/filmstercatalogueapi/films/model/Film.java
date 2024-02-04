package com.alexic0n.filmstercatalogueapi.films.model;


import com.alexic0n.filmstercatalogueapi.AbstractCatalogueEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;
import java.util.Date;

@Document
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Film extends AbstractCatalogueEntity {

        @NotEmpty
        private String title;
        @NotEmpty
        private String director;
        @NotEmpty
        private String genre;
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date releaseDate;
        @NotEmpty
        private String description;

        public Film(
                String title,
                String director,
                String genre,
                Date releaseDate,
                String description
        ) {
                this.title = title;
                this.director = director;
                this.genre = genre;
                this.releaseDate = releaseDate;
                this.description = description;
        }
}
