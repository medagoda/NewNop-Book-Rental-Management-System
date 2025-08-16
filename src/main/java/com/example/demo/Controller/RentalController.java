package com.example.demo.Controller;

import com.example.demo.Dto.RentalDto;
import com.example.demo.Entity.BookEntity;
import com.example.demo.Service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rent")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<RentalDto>> findAll() {
        List<RentalDto> rentals = rentalService.findAll();
        if (rentals.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if empty
        }
        return ResponseEntity.ok(rentals); // 200 OK with list
    }

    // CREATE new rental
    @PostMapping
    public ResponseEntity<RentalDto> create(@Valid @RequestBody BookEntity bookEntity) {
        RentalDto createdRental = rentalService.create(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
    }

    // UPDATE existing rental
    @PutMapping("/{id}")
    public ResponseEntity<RentalDto> update(
            @PathVariable Long id,
            @Valid @RequestBody RentalDto rentalDto) {

        return rentalService.update(id, rentalDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
