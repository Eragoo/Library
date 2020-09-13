package com.Eragoo.Library.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class BookCommand {
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotNull(message = "authorId must be specified")
    private Long authorId;
    @NotNull(message = "publicationYear field must be specified")
    private Integer publicationYear;
    @NotNull(message = "amount field must be specified")
    private Integer amount;
    @NotEmpty(message = "Book must contain at least 1 genre")
    private Set<Long> genreIds;
}
