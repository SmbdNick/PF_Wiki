package ru.kolyan.pathfinder.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kolyan.pathfinder.exception.dto.ErrorResponse;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionController {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ConflictException.class})
    @ResponseBody
    public ErrorResponse handleConflict(ConflictException e) {
        log.error("Conflict: {} {}", e.getMessage(), e.getStackTrace());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    @ResponseBody
    public ErrorResponse handleNotFound(NotFoundException e) {
        log.error("NotFound: {} {}", e.getMessage(), e.getStackTrace());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ErrorResponse handleValidation(MethodArgumentNotValidException e) {
        log.error("Bad Request: {} {}", e.getMessage(), e.getStackTrace());

        List<ErrorResponse.Error> errors = new ArrayList<>();
        e.getFieldErrors().forEach(error -> errors.add(ErrorResponse.Error.builder()
                        .message(error.getDefaultMessage())
                        .build()
                )
        );

        return ErrorResponse.builder()
                .errors(errors)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ErrorResponse handleBadRequest(BadRequestException e) {
        log.error("BadRequest: {} {}", e.getMessage(), e.getStackTrace());
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
