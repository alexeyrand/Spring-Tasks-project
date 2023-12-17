package com.alexeyrand.task.api.store.api.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Класс, хендлер, который перехватывает ошибки. В нашем случае все подряд
 */
@Log4j2
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object>  exception(Exception ex, WebRequest request) throws Exception {
        log.error("Exception during executon of application", ex);
        return handleException(ex, request);
    }
}
