package ru.itmentor.spring.boot_security.demo.util;

public class UserNotUpdatedException extends RuntimeException {
    public UserNotUpdatedException(String message) {
        super(message);
    }
}
