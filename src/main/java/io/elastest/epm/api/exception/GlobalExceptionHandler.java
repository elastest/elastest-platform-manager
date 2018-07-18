package io.elastest.epm.api.exception;

import io.elastest.epm.exception.AllocationException;
import io.elastest.epm.exception.BadRequestException;
import io.elastest.epm.exception.NotFoundException;
import io.elastest.epm.exception.TerminationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({
            AllocationException.class,
            TerminationException.class,
            BadRequestException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleBadRequestException(Exception e, WebRequest request) {

        log.error("Exception was thrown -> Return message: " + e.getMessage(), e);

        ApiException exc = new ApiException(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, exc, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request) {

        log.error("Exception was thrown -> Return message: " + e.getMessage(), e);

        ApiException exc = new ApiException(HttpStatus.NOT_FOUND.value(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, exc, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleOtherException(Exception e, WebRequest request) {

        log.error("Exception was thrown -> Return message: " + e.getMessage(), e);

        ApiException exc = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, exc, headers, HttpStatus.NOT_FOUND, request);
    }
}
