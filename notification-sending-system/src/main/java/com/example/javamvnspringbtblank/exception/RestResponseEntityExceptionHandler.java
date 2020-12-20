package com.example.javamvnspringbtblank.exception;

import com.example.javamvnspringbtblank.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.InvocationTargetException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    LoggingService logServ;

    /**
     * Catch all for any other exceptions.
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<?> handleAnyException(Exception e) {
        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Catch custom NotificationException exceptions.
     */
    @ExceptionHandler({NotificationException.class})
    @ResponseBody
    public ResponseEntity<?> handleNotificationException(Exception e) {
        return errorResponse(e, HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Handle failures commonly thrown from code.
     */
    @ExceptionHandler({InvocationTargetException.class, IllegalArgumentException.class, ClassCastException.class,
            ConversionFailedException.class})
    @ResponseBody
    public ResponseEntity<?> handleMiscFailures(Throwable t) {
        return errorResponse(t, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<?> errorResponse(Throwable throwable, HttpStatus status) {
        if (throwable != null) {
            logServ.logException(throwable);
            return new ResponseEntity<>(throwable.getMessage(), status);
        } else {
            return response(null, status);
        }
    }

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }
}
