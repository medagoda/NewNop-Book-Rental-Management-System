package com.example.demo.Dao;

import com.example.demo.Entity.BookEntity;
import com.example.demo.Enums.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookDao extends JpaRepository<BookEntity, Long> {


    Optional<BookEntity> findByTitle(String title);

    Optional<BookEntity> findById(Long id);

}
