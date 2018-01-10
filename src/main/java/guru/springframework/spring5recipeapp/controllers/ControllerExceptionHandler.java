package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.exceptions.NumberValueFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jt on 7/14/17.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberValueFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception) {
        log.error("Handling number format exception");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("400Error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
}
