package com.example.demo.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            "User Not Found",
            HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            "Email Already Exists",
            HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            "Invalid Credentials",
            HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<?> handlePasswordMismatch(PasswordMismatchException ex) {
        ErrorDetails error = new ErrorDetails(
            LocalDateTime.now(),
            ex.getMessage(),
            "Password Mismatch",
            HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        for (ObjectError error : allErrors) {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        }

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTime(LocalDateTime.now());
        errorDetails.setPath("Validation Failed");
        errorDetails.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setHashMap(errors);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	
	
}
