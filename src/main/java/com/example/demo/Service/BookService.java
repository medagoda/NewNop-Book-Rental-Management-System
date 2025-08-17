package com.example.demo.Service;

import com.example.demo.Dto.BookDto;
import java.util.List;


public interface BookService {
    BookDto create(BookDto bookDto);

    List<BookDto> findAll();

    boolean deleteById(Long id);

    BookDto updateBook(Long id, BookDto bookDto);


}
