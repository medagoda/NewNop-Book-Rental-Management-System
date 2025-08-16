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

    private UserTransformer userTransformer;

    public RentalEntity DtoToRental(RentalDto rentalDto){
        if (rentalDto == null) {
            return null;
        } else {
            RentalEntity rental = new RentalEntity();
            rental.setId(rentalDto.getId());
            rental.setReturned(rentalDto.isReturned());
            rental.setBook(bookTransformer.DTOtoBook(rentalDto.getBookDto()));
            rental.setReturnDate(rentalDto.getReturnDate());
            rental.setUser(userTransformer.dtoToUser(rentalDto.getUserDto()));
            rental.setRentalDate(rentalDto.getRentalDate());
            return rental;
        }
    }

    public RentalDto rentalToDto(RentalEntity rentalEntity){
        if (rentalEntity == null) {
            return null;
        } else {
            RentalDto rentalDto = new RentalDto();
            rentalDto.setId(rentalEntity.getId());
            rentalDto.setReturned(rentalEntity.isReturned());
            rentalDto.setBookDto(bookTransformer.BookToDTO(rentalEntity.getBook()));
            rentalDto.setReturnDate(rentalEntity.getReturnDate());
            rentalDto.setUserDto(userTransformer.userToDto(rentalEntity.getUser()));
            rentalDto.setRentalDate(rentalEntity.getRentalDate());
            return rentalDto;
        }
    }

    }


