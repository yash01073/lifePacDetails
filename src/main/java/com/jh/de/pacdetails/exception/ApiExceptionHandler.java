package com.jh.de.pacdetails.exception;

import com.jh.de.pacdetails.svc.NewRelicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private NewRelicService newRelicService;

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiError> handleApiException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),ex.getErrorList());
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(ApiException.BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ApiError> handleApiBadRequestException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST,ex.getMessage(),ex.getErrorList());
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ApiException.InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ApiError> handleApiInternalServerErrorException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),ex.getErrorList());
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(ApiException.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ApiError> handleApiNtFoundException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.NOT_FOUND,ex.getMessage(),ex.getErrorList());
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(ApiException.BadResponseException.class)
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    protected ResponseEntity<ApiError> handleApiBadResponseException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.PARTIAL_CONTENT,ex.getMessage(),ex.getErrorList());
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ApiException.BadVPasOneResponseException.class)
    @ResponseStatus(HttpStatus.METHOD_FAILURE)
    protected ResponseEntity<ApiError> handleVPASOOneApiBadResponseException(ApiException ex, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.METHOD_FAILURE,ex.getMessage(),ex.getErrorList());
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    protected ResponseEntity<ApiError> handleSQLTransientException(SQLException ex) {
        newRelicService.populateErrorDetails(ex.getMessage());
        ApiError apiError = new ApiError(LocalDateTime.now(),HttpStatus.REQUEST_TIMEOUT,"Request Timed out",List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    protected ResponseEntity<ApiError> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(LocalDateTime.now(),status,"Unknown error",List.of(ex.getMessage()));
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            apiError.setMessage("Internal server error");
            //request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        newRelicService.populateErrorDetails(apiError);
        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }

}
