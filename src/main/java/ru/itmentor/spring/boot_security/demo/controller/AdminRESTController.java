package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.AppService;
import ru.itmentor.spring.boot_security.demo.util.UserErrorResponse;
import ru.itmentor.spring.boot_security.demo.util.UserNotCreatedException;
import ru.itmentor.spring.boot_security.demo.util.UserNotFoundException;
import ru.itmentor.spring.boot_security.demo.util.UserNotUpdatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final AppService appService;

    @Autowired
    public AdminRESTController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("list")
    public List<User> getAllUsers() {
        return appService.getAllUsers();
    }

    @GetMapping({"{id}"})
    public User readUser(@PathVariable("id") long id) {
        return appService.readUser(id);
    }

    @PostMapping("new")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            List<FieldError> allErrors = bindingResult.getFieldErrors();
            for (FieldError error : allErrors) {
                errors.append(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new UserNotCreatedException(errors.toString());
        }
        appService.saveUser(user, bindingResult, model);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            appService.readUser(id);
        }
        catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        appService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") long id, @Valid @RequestBody User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            List<FieldError> allErrors = bindingResult.getFieldErrors();
            for (FieldError error : allErrors) {
                errors.append(error.getField() + " - " + error.getDefaultMessage() + ";");
            }
            throw new UserNotUpdatedException(errors.toString());
        }

        appService.saveUser(user, bindingResult, model);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn`t found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotUpdatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
