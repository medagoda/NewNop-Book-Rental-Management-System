package com.example.demo.Transformers;

import com.example.demo.Dto.BookDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Enums.AvailabilityStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class BookTransformer {
    public BookDto BookToDTO(BookEntity book) {
        if (book == null) {
            return null;
        } else {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setTitle(book.getTitle());
            bookDto.setGenre(book.getGenre());
            bookDto.setAvailabilityStatus(book.isBorrowed() ? AvailabilityStatus.CHECKED_OUT : AvailabilityStatus.AVAILABLE);
            return bookDto;
        }
    }

    public BookEntity DTOtoBook(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        } else {
            BookEntity book = new BookEntity();
            book.setId(bookDto.getId());
            book.setAuthor(bookDto.getAuthor());
            book.setTitle(bookDto.getTitle());
            book.setGenre(bookDto.getGenre());
            book.setAvailabilityStatus(bookDto.getAvailabilityStatus());
            return book;
        }
    }

}

