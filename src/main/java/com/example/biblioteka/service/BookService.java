package com.example.biblioteka.service;

import com.example.biblioteka.model.Author;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Category;
import com.example.biblioteka.model.Country;
import com.example.biblioteka.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findById(Long id);

    List<Book> findAll();

    //Optional<Book> findByName(String name);

    Optional<Book> save(BookDto bookDto);
    //Optional<Book> save(String name, Category category, Long author, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);
    //Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies);

    void deleteById(Long id);

    void markAsTaken(Long id);

}
