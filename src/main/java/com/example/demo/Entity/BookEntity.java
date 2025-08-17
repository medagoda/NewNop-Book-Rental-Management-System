package com.example.demo.Entity;

import com.example.demo.Enums.AvailabilityStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title must be between 4 and 20 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author must be between 5 and 20 characters")
    private String author;

    @Column(nullable = false)
    @NotBlank(message = "Genre is required")
    private String genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status", nullable = false)
    @NotNull
    private AvailabilityStatus availabilityStatus;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<RentalEntity> rental;

    private boolean isBorrowed;
}
