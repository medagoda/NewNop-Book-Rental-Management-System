package com.example.demo.Dto;

import com.example.demo.Enums.AvailabilityStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    private Long id;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author must be between 5 and 20 characters")
    private String author;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title must be between 4 and 20 characters")
    private String title;

    private boolean isBorrowed;

    @Column(nullable = false)
    @NotBlank(message = "Genre is required")
    private String genre;

    private AvailabilityStatus availabilityStatus;

}
