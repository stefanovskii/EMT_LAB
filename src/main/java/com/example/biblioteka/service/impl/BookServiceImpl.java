package com.example.biblioteka.service.impl;

import com.example.biblioteka.model.Author;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Category;
import com.example.biblioteka.model.dto.BookDto;
import com.example.biblioteka.model.exceptions.AuthorNotFoundException;
import com.example.biblioteka.model.exceptions.BookNotFoundException;
import com.example.biblioteka.repository.AuthorRepository;
import com.example.biblioteka.repository.BookRepository;
import com.example.biblioteka.service.AuthorService;
import com.example.biblioteka.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    /*
    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }*/

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorService.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    /*@Override
    public Optional<Book> save(String name, Category category, Long author, Integer availableCopies) {
        Author author1 = this.authorService.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));
        Book book = new Book(name, category, author1, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }*/
    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        Author author = this.authorService.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);

    }
    /*@Override
    public Optional<Book> edit(Long id, String name, Category category, Long author, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        Author author1 = this.authorService.findById(author)
                .orElseThrow(() -> new AuthorNotFoundException(author));
        book.setAuthor(author1);

        this.bookRepository.save(book);
        return Optional.of(book);
    }*/

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public void markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if(book.getAvailableCopies() > 0){
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        this.bookRepository.save(book);
    }
}
