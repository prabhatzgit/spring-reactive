package com.pkg.springreactiveexceptionhandler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Book {
    private int bookId;
    private String name;
    private double price;
}