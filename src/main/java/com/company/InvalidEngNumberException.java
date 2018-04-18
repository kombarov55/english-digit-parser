package com.company;

public class InvalidEngNumberException extends RuntimeException {

    public InvalidEngNumberException() {
    }

    public InvalidEngNumberException(String msg) {
        super("invalid line for input: " + msg);
    }
}
