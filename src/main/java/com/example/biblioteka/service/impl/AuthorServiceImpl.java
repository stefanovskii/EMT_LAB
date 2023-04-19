package com.example.biblioteka.service.impl;

import com.example.biblioteka.model.Author;
import com.example.biblioteka.model.Country;
import com.example.biblioteka.model.dto.AuthorDto;
import com.example.biblioteka.model.exceptions.AuthorNotFoundException;
import com.example.biblioteka.model.exceptions.CountryNotFoundException;
import com.example.biblioteka.repository.AuthorRepository;
import com.example.biblioteka.repository.CountryRepository;
import com.example.biblioteka.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }
    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.of(this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id)));
    }
    /*@Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }*/

    /*@Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));


        Author author = new Author(name,surname,country);
        return Optional.of(this.authorRepository.save(author));
    }*/
    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));


        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        return Optional.of(this.authorRepository.save(author));
    }
    /*@Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        this.authorRepository.save(author);

        return Optional.of(author);
    }*/
    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        Country country = this.countryRepository.findById(authorDto.getCountry()).orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);

        this.authorRepository.save(author);

        return Optional.of(author);
    }
    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }

}
