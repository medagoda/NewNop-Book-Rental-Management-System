package com.example.demo.Service;

import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.RentalDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.RentalEntity;

import java.util.List;
import java.util.Optional;

public interface RentalService {

    List<RentalDto> findAll();

    RentalDto create(BookEntity bookEntity);

    RentalDto returnBook(RentalEntity rentalEntity);

    Optional<RentalDto> update(Long id, RentalDto rentalDto);

}
