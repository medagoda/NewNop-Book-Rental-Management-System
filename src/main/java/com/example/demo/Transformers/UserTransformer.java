package com.example.demo.Transformers;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {
    public UserDto userToDto(UserEntity user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setRentalDays(user.getRentalDays());
        return dto;
    }

    public UserEntity dtoToUser(UserDto dto) {
        if (dto == null) return null;

        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setRentalDays(dto.getRentalDays());
        return user;
    }
}
