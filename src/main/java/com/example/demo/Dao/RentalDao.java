package com.example.demo.Dao;

import com.example.demo.Entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalDao extends JpaRepository<RentalEntity, Long> {
    @Query("SELECT r FROM RentalEntity r JOIN FETCH r.book")
    List<RentalEntity> findAllWithBooks();
}
