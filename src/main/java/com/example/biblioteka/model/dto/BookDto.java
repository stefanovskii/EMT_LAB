package com.example.biblioteka.model.dto;

import com.example.biblioteka.model.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class BookDto {

    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long author;
    private Integer availableCopies;

    public BookDto() {}

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
