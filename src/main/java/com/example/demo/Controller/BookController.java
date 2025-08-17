package com.example.demo.Controller;

import com.example.demo.Dto.BookDto;
import com.example.demo.Enums.AvailabilityStatus;
import com.example.demo.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {
        List<BookDto> books = bookService.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 if no books
        }
        return ResponseEntity.ok(books); // 200 with list of books
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto bookDto) {

        // Set default status if null
        if (bookDto.getAvailabilityStatus() == null) {
            bookDto.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        }
        BookDto createdBook = bookService.create(bookDto);
        // Return 201 CREATED with the created book
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @PathVariable Long id,
            @RequestBody BookDto bookDto) {

        BookDto updatedBook = bookService.updateBook(id, bookDto);
        return ResponseEntity.ok(updatedBook);
    }

}
