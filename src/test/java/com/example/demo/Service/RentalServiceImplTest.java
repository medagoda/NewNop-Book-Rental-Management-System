package com.example.demo.Service;

import com.example.demo.Dao.BookDao;
import com.example.demo.Dao.RentalDao;
import com.example.demo.Dto.RentalDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Entity.RentalEntity;
import com.example.demo.Enums.AvailabilityStatus;
import com.example.demo.Transformers.BookTransformer;
import com.example.demo.Transformers.RentalTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalServiceImplTest {

    @InjectMocks
    private RentalServiceImpl rentalService;

    @Mock
    private BookDao bookDao;

    @Mock
    private RentalDao rentalDao;

    @Mock
    private RentalTransformer rentalTransformer;

    @Mock
    private BookService bookService;

    @Mock
    private BookTransformer bookTransformer;

    private BookEntity availableBook;
    private RentalEntity rentalEntity;
    private RentalDto rentalDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        availableBook = new BookEntity();
        availableBook.setId(1L);
        availableBook.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        availableBook.setBorrowed(false);

        rentalEntity = RentalEntity.builder()
                .id(1L)
                .book(availableBook)
                .name("John Doe")
                .email("john@example.com")
                .phone("123456789")
                .rentalDays(7)
                .rentalDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(30))
                .returned(false)
                .build();

        rentalDto = RentalDto.builder()
                .id(1L)
                .bookId(1L)
                .name("John Doe")
                .email("john@example.com")
                .phone("123456789")
                .rentalDays(7)
                .build();
    }

    // Test findAll
    @Test
    void testFindAll() {
        when(rentalDao.findAll()).thenReturn(Arrays.asList(rentalEntity));
        when(rentalTransformer.rentalToDto(rentalEntity)).thenReturn(rentalDto);

        List<RentalDto> rentals = rentalService.findAll();

        assertNotNull(rentals);
        assertEquals(1, rentals.size());
        assertEquals("John Doe", rentals.get(0).getName());

        verify(rentalDao, times(1)).findAll();
        verify(rentalTransformer, times(1)).rentalToDto(rentalEntity);
    }

    // Test create
    @Test
    void testCreate() {
        when(bookDao.findById(1L)).thenReturn(Optional.of(availableBook));
        when(rentalDao.save(any(RentalEntity.class))).thenReturn(rentalEntity);
        when(rentalTransformer.rentalToDto(rentalEntity)).thenReturn(rentalDto);

        RentalDto createdRental = rentalService.create(rentalDto);

        assertNotNull(createdRental);
        assertEquals("John Doe", createdRental.getName());
        verify(bookDao, times(1)).findById(1L);
        verify(bookService, times(1)).markAsBorrowed(1L);
        verify(rentalDao, times(1)).save(any(RentalEntity.class));
    }
}
