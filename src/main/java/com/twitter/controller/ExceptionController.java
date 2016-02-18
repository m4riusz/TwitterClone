package com.twitter.controller;

import com.twitter.model.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mariusz on 18.02.16.
 */
@ControllerAdvice
public class ExceptionController {
    Logger logger = LogManager.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse exceptionHandler(HttpServletRequest req, Exception exception) {
        ErrorResponse response = new ErrorResponse();
        response.setUrl(req.getRequestURI());
        response.setMessage(exception.getMessage());
        logger.error(exception.getClass().getName() + " " + req.getParameterMap() + " " + response);
        return response;
    }

}
