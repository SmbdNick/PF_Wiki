package ru.kolyan.pathfinder.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
public class ErrorResponse {
    private String message;
    private List<Error> errors;

    @Getter
    @Builder
    @Jacksonized
    public static class Error{
        private String code;
        private String message;
    }
}
