package com.pkg.springreactiveexceptionhandler.exceptionhandler;

public class BookAPIException extends Exception{

    public BookAPIException(String message) {
        super(message);
    }
}