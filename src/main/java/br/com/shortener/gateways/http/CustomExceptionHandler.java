package br.com.shortener.gateways.http;

import br.com.shortener.exceptions.RecordNotFoundException;
import br.com.shortener.gateways.http.json.response.ErrorResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorResponseJson handleException(Throwable ex) {
        return new ErrorResponseJson(ex.getMessage());
    }

}
