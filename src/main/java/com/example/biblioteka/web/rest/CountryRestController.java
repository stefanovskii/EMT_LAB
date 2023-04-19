package com.example.biblioteka.web.rest;

import com.example.biblioteka.model.Book;
import com.example.biblioteka.model.Country;
import com.example.biblioteka.model.dto.BookDto;
import com.example.biblioteka.model.dto.CountryDto;
import com.example.biblioteka.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return this.countryService.findAll();
    }
}
