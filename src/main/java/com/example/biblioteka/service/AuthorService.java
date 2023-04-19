package com.example.biblioteka.service;

import com.example.biblioteka.model.Author;
import com.example.biblioteka.model.Country;
import com.example.biblioteka.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    //Optional<Author> findByName(String name);

    Optional<Author> save(AuthorDto authorDto);
    //Optional<Author> save(String name, String surname, Long country);

    Optional<Author> edit(Long id, AuthorDto authorDto);
    //Optional<Author> edit(Long id, String name, String surname, Long country);

    void deleteById(Long id);
}
