package com.twitter.controller;

import com.twitter.exception.*;
import com.twitter.model.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mariusz on 18.02.16.
 */
@ControllerAdvice
public class ExceptionController {
    Logger logger = LogManager.getLogger(ExceptionController.class);

    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {TweetNotFoundException.class, UserNotFollowedException.class})
    public ErrorResponse notFoundHandler(HttpServletRequest req, Exception exception) {
        return generateErrorResponse(req, exception);
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {TweetCreateException.class, UserCreateException.class
            , UserEditException.class, TweetGetException.class})
    public ErrorResponse badRequestHandler(HttpServletRequest req, Exception exception) {
        return generateErrorResponse(req, exception);
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {UserNotFollowedException.class, UserAlreadyFollowed.class,
            UserFollowException.class, UserAlreadyExist.class})
    public ErrorResponse conflictHandler(HttpServletRequest req, Exception exception) {
        return generateErrorResponse(req, exception);
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {TweetDeleteException.class, PermisionException.class})
    public ErrorResponse permisionErrorHandler(HttpServletRequest req, Exception exception) {
        return generateErrorResponse(req, exception);
    }

    private ErrorResponse generateErrorResponse(HttpServletRequest req, Exception exception) {
        ErrorResponse response = new ErrorResponse();
        response.setUrl(req.getRequestURI());
        response.setMessage(exception.getMessage());
        logger.error(exception.getClass().getName() + " " + req.getParameterMap() + " " + response);
        return response;
    }

}
