package com.example.demo.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.Dao.BookDao;
import com.example.demo.Dto.BookDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Enums.AvailabilityStatus;
import com.example.demo.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookDao bookDao;

    private BookEntity availableBook;
    private BookEntity rentedBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        availableBook = new BookEntity();
        availableBook.setId(1L);
        availableBook.setTitle("Available Book");
        availableBook.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);

        rentedBook = new BookEntity();
        rentedBook.setId(2L);
        rentedBook.setTitle("Rented Book");
        rentedBook.setAvailabilityStatus(AvailabilityStatus.CHECKED_OUT);
    }

    // Test Case 1: Adding a book
    @Test
    void testAddBook() {
        // Convert entity to DTO before calling service
        BookDto bookDto = new BookDto();
        bookDto.setTitle(availableBook.getTitle());
        bookDto.setAuthor(availableBook.getAuthor());
        bookDto.setGenre(availableBook.getGenre());

        when(bookDao.save(any(BookEntity.class))).thenReturn(availableBook);

        BookDto savedBook = bookService.create(bookDto); // pass DTO, not entity

        assertNotNull(savedBook);
        assertEquals("Available Book", savedBook.getTitle());
        verify(bookDao, times(1)).save(any(BookEntity.class));
    }


    // Test Case 2: DeleteById a book (should throw exception)
    @Test
    void testDeleteRentedBook_ShouldThrowException() {
        // Mock the repository to return a rented book
        when(bookDao.findById(2L)).thenReturn(Optional.of(rentedBook));

        // Assert that trying to delete a rented book throws an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookService.deleteById(2L);
        });

        assertEquals("Cannot delete a rented book", exception.getMessage());

        // Verify that deleteById was never called
        verify(bookDao, never()).deleteById(2L);
    }
}
