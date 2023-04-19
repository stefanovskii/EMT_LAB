package com.example.biblioteka.repository;

import com.example.biblioteka.model.Author;
import com.example.biblioteka.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByName(String name);
    List<Author> findAllByCountry(Country country);
}
