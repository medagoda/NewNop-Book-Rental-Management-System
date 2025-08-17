package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "rents")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "returned")
    private boolean returned;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @NotBlank
    @Size(min = 5, max = 20)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_no")
    private String phone;

    @Column(name = "rental_days")
    private Integer rentalDays;

}
