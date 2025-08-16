package com.example.demo.Dto;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.UserEntity;
import jakarta.validation.constraints.NotNull;
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

    private BookDto bookDto;

    @NotNull
    private LocalDate rentalDate;

    @NotNull
    private LocalDate returnDate;

    private boolean returned;

    @NotNull
    private UserDto userDto;

}
