package com.jh.de.pacdetails.exception;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private List<String> errorList;

    public ApiException(String message) {
        super(message);
        this.errorList = List.of();
    }
    public ApiException(String message, List<String> errorList) {
        super(message);
        this.errorList = errorList;
    }
    public static class BadRequestException extends ApiException {
        public BadRequestException(String message) {
            super(message);
        }
        public BadRequestException(String message, List<String> errorList) {
            super(message, errorList);
        }
    }
    public static class NotFoundException extends ApiException {
        public NotFoundException(String message, List<String> errorList) {
            super(message, errorList);
        }
    }
    public static class BadResponseException extends ApiException {
        public BadResponseException(String message) {
            super(message);
        }
        public BadResponseException(String message, List<String> errorList) {
            super(message, errorList);
        }
    }

    public static class BadVPasOneResponseException extends ApiException {
        public BadVPasOneResponseException(String message) {
            super(message);
        }
        public BadVPasOneResponseException(String message, List<String> errorList) {
            super(message, errorList);
        }
    }

    public static class InternalServerException extends ApiException {
        public InternalServerException(String message) {
            super(message);
        }
        public InternalServerException(String message, List<String> errorList) {
            super(message, errorList);
        }
    }

}
