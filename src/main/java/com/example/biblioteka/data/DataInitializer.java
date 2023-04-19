package com.example.biblioteka.data;

import com.example.biblioteka.model.Author;
import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Category;
import com.example.biblioteka.model.Country;
import com.example.biblioteka.repository.AuthorRepository;
import com.example.biblioteka.repository.BookRepository;
import com.example.biblioteka.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        if (countryRepository.count() != 0 || authorRepository.count() != 0 || bookRepository.count() != 0) {
            return;
        }

        for (int i = 1; i < 11; i++) {
            Country c = new Country();
            c.setName(String.format("Country %d", i));
            c.setContinent(String.format("Continent %d", i));
            countryRepository.save(c);

            Author a = new Author();
            a.setName(String.format("Name %d", i));
            a.setSurname(String.format("Surname %d", i));
            a.setCountry(c);
            authorRepository.save(a);

            Book b = new Book();
            b.setName(String.format("Name %d", i));
            b.setCategory(Category.values()[i % Category.values().length]);
            b.setAuthor(a);
            b.setAvailableCopies(i);
            bookRepository.save(b);
        }
    }
}
