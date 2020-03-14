package mx.com.cinepolis.anuncios.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.com.cinepolis.anuncios.api.exceptions.DivByZeroException;

@ControllerAdvice
public class CustomControllerAdvice {
	// https://developpaper.com/controller-advice-interceptor/
	
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, Object> methodArgumentNotValidExceptionErrorHandler(MethodArgumentNotValidException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return body;
    }
    
    @ResponseBody
    @ExceptionHandler(value = DivByZeroException.class)
    public Map<String, Object> divByZeroExceptionErrorHandler(DivByZeroException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("Code", ex.getCode());
        body.put("Short", ex.getShortMsg());
        body.put("Long", ex.getLongMsg());
        return body;
    }

}
