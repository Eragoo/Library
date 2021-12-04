package com.Eragoo.Library.book.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Set;

@Setter
@Getter
public class BookInputDto {
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotNull(message = "authorId must be specified")
    private Long authorId;
    @NotNull(message = "publicationYear field must be specified")
    private Integer publicationYear;
    @NotNull(message = "amount field must be specified")
    @Min(value = 1, message = "Amount field must be greater than 0")
    private Integer amount;
    @NotEmpty(message = "Book must contain at least 1 genre")
    private Set<Long> genreIds;
}
