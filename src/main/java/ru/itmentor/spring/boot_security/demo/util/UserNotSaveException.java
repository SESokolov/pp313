package ru.itmentor.spring.boot_security.demo.util;

public class UserNotSaveException extends RuntimeException {
    public UserNotSaveException(String message) {
        super(message);
    }
}
