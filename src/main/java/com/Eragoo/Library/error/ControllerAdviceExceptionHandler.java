package com.Eragoo.Library.error;

import com.Eragoo.Library.error.exception.ConflictException;
import com.Eragoo.Library.error.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

@Slf4j
@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFound(NotFoundException ex) {
        log.error(ex.getMessage());
        ErrorResponseDto responseDto = new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Not found"));
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDto> handleConflict(ConflictException ex) {
        log.error(ex.getMessage());
        ErrorResponseDto responseDto = new ErrorResponseDto(defaultIfEmpty(ex.getMessage(), "Conflict"));
        return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.error(ex.getMessage());
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<String> validationErrors = getValidationErrors(allErrors);
        Map<String, List<String>> response = Map.of("errors", validationErrors);
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private List<String> getValidationErrors(List<ObjectError> allErrors) {
        List<String> validationErrors = new ArrayList<>();

        allErrors.forEach(e -> {
            String defaultMessage = e.getDefaultMessage();
            validationErrors.add(defaultMessage);
        });
        return validationErrors;
    }
}
