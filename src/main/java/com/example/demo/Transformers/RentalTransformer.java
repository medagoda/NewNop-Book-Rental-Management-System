package com.example.demo.Transformers;

import com.example.demo.Dto.RentalDto;
import com.example.demo.Entity.RentalEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalTransformer {

    @Autowired
    private BookTransformer bookTransformer;

    public RentalEntity DtoToRental(RentalDto rentalDto){
        if (rentalDto == null) {
            return null;
        } else {
            RentalEntity rental = new RentalEntity();
            rental.setId(rentalDto.getId());
            rental.setReturned(rentalDto.isReturned());
            rental.setReturnDate(rentalDto.getReturnDate());
            rental.setRentalDate(rentalDto.getRentalDate());
            rental.setName(rentalDto.getName());
            rental.setEmail(rentalDto.getEmail());
            rental.setPhone(rentalDto.getPhone());
            rental.setRentalDays(rentalDto.getRentalDays());
            return rental;
        }
    }

    public RentalDto rentalToDto(RentalEntity rentalEntity){
        if (rentalEntity == null) {
            return null;
        } else {
            RentalDto rentalDto = new RentalDto();
            rentalDto.setId(rentalEntity.getId());
            rentalDto.setBookId(rentalEntity.getBook().getId());
            rentalDto.setReturned(rentalEntity.isReturned());
            rentalDto.setBookDto(bookTransformer.BookToDTO(rentalEntity.getBook()));
            rentalDto.setReturnDate(rentalEntity.getReturnDate());
            rentalDto.setRentalDate(rentalEntity.getRentalDate());
            rentalDto.setName(rentalEntity.getName());
            rentalDto.setEmail(rentalEntity.getEmail());
            rentalDto.setPhone(rentalEntity.getPhone());
            rentalDto.setRentalDays(rentalEntity.getRentalDays());
            return rentalDto;
        }
    }

    }


