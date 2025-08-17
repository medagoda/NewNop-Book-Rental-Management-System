package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalDto {

    private Long id;

    private Long bookId;

    private BookDto bookDto;

    private LocalDate rentalDate;

    private LocalDate returnDate;

    private boolean returned;

    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    private String email;

    private String phone;

    private Integer rentalDays;
}
