package com.example.demo.Dao;

import com.example.demo.Entity.RentalEntity;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalDao extends JpaRepository<RentalEntity, Long> {
    List<RentalEntity> findAllByUser(UserEntity user);
}
