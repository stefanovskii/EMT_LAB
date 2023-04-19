package com.example.biblioteka.service;

import com.example.biblioteka.model.Country;
import com.example.biblioteka.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    Optional<Country> findById(Long id);

    List<Country> findAll();

    //Optional<Country> findByName(String name);
    //List<Country> findAllByContinent(String continent);

    Optional<Country> save(CountryDto countryDto);
    //Optional<Country> save(String name, String continent);

    Optional<Country> edit(Long id, CountryDto countryDto);
    //Optional<Country> edit(Long id, String name, String continent);

    void deleteById(Long id);


}
