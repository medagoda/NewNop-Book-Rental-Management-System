package com.example.demo.Service;

import com.example.demo.Dao.BookDao;
import com.example.demo.Dao.RentalDao;
import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.RentalDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.RentalEntity;
import com.example.demo.Transformers.BookTransformer;
import com.example.demo.Transformers.RentalTransformer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RentalServiceImpl implements RentalService{

    private final BookService bookService;
    private final RentalDao rentalDao;
    private final BookTransformer bookTransformer;
    private final RentalTransformer rentalTransformer;
    private final BookDao bookDao;
    private final int RENT_PERIOD = 30;

    @Override
    public List<RentalDto> findAll() {
        return rentalDao.findAll()
                .stream()
                .map(rentalTransformer::rentalToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RentalDto create(RentalDto rentalDto) {
        if (rentalDto == null) {
            throw new RuntimeException("Rental data is required");
        }

        // fetch the book from DB instead of directly converting DTO
        BookEntity book = bookDao.findById(rentalDto.getBookDto().getId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + rentalDto.getBookDto().getId()));

        if (book.isBorrowed()) {
            throw new RuntimeException("You can't rent a borrowed book");
        }

        book.setBorrowed(true);
        bookService.updateBook(book.getId(), bookTransformer.BookToDTO(book));

        LocalDate actualDate = LocalDate.now();

        RentalEntity rentalEntity = RentalEntity.builder()
                .book(book)
                .name(rentalDto.getName())
                .email(rentalDto.getEmail())
                .phone(rentalDto.getPhone())
                .rentalDays(rentalDto.getRentalDays())
                .rentalDate(actualDate)
                .returnDate(actualDate.plusDays(RENT_PERIOD))
                .returned(false)
                .build();

        RentalEntity savedRental = rentalDao.save(rentalEntity);

        return rentalTransformer.rentalToDto(savedRental);
    }


    @Override
    public RentalDto returnBook(RentalEntity rentalEntity) {
        if (rentalEntity != null) {
            if (!rentalEntity.isReturned()) {
                BookEntity rentBook = rentalEntity.getBook();
                rentBook.setBorrowed(false);
                bookService.updateBook(rentBook.getId(), bookTransformer.BookToDTO(rentBook));
                rentalEntity.setReturned(true);

            }
            return rentalTransformer.rentalToDto(rentalDao.save(rentalEntity));
        } else {
            throw new RuntimeException("Incorrect rental!");
        }
    }

    @Override
    public Optional<RentalDto> update(Long id, RentalDto rentalDto) {
        return rentalDao.findById(id)
                .map(existingRental -> {
                    // Update rental fields
                    existingRental.setRentalDate(rentalDto.getRentalDate());
                    existingRental.setReturnDate(rentalDto.getReturnDate());

                    // Optional: update book if needed
                    if (!existingRental.getBook().getId().equals(rentalDto.getBookDto().getId())) {
                        BookEntity newBook = bookDao.findById(rentalDto.getBookDto().getId())
                                .orElseThrow(() -> new RuntimeException("Book not found"));
                        existingRental.setBook(newBook);
                    }

                    RentalEntity updatedRental = rentalDao.save(existingRental);
                    return rentalTransformer.rentalToDto(updatedRental);
                });
    }


}
