package com.example.demo.Service;

import com.example.demo.Dao.BookDao;
import com.example.demo.Dto.BookDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Enums.AvailabilityStatus;
import com.example.demo.Transformers.BookTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;

    private final BookTransformer bookTransformer;


    public BookDto findById(Long id) {
        BookEntity book = bookDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return bookTransformer.BookToDTO(book);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto != null) {
            /* if (bookDto.getAvailabilityStatus() == null) {
                bookDto.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            }

             */

            BookEntity mapped = bookTransformer.DTOtoBook(bookDto);
            return bookTransformer.BookToDTO(bookDao.save(mapped));
        }
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return bookDao.findAll()
                .stream()
                .map(bookTransformer::BookToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null && bookDao.existsById(id)) {
            bookDao.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public BookDto updateBook(Long id, BookDto bookDto) {
        BookEntity existingBook = bookDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update fields
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setGenre(bookDto.getGenre());
        existingBook.setAvailabilityStatus(         //Update the book’s availability status only if the client provided a value. Otherwise, leave it as it is
                bookDto.getAvailabilityStatus() != null ? bookDto.getAvailabilityStatus() : existingBook.getAvailabilityStatus()
        );
        existingBook.setBorrowed(bookDto.isBorrowed());

        BookEntity savedBook = bookDao.save(existingBook);

        return bookTransformer.BookToDTO(savedBook);
    }



}
