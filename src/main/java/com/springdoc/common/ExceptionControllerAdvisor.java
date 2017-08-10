package com.springdoc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * @author Grissom
 */
@ControllerAdvice(annotations = Controller.class)
public class ExceptionControllerAdvisor {
    private static final String ERROR_MSG_FORMAT = "%s";
    private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvisor.class);

    /**
     * @param response
     * @param ex
     * @throws IOException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                String.format(ERROR_MSG_FORMAT, ex.getMessage()));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    void handleViolationExceptionBadRequests(HttpServletResponse response, ConstraintViolationException ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                String.format(ERROR_MSG_FORMAT, ex.getConstraintViolations().iterator().next().getMessage()));
    }


    @ExceptionHandler(SQLException.class)
    void handleSQLRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                String.format(ERROR_MSG_FORMAT, "数据库错误：" + ex.getMessage()));
    }


    /**
     * 自定义数据校验异常处理
     *
     * @param response
     * @param ex
     * @throws IOException
     */
    @ExceptionHandler(ValidationException.class)
    void handleValidatorRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                String.format(ERROR_MSG_FORMAT, ex.getMessage()));
    }

    /**
     * @param response
     * @param ex
     * @throws IOException
     */
    @ExceptionHandler(SecurityException.class)
    void handleSecurityRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.FORBIDDEN.value(),
                String.format(ERROR_MSG_FORMAT, ex.getMessage()));
    }

    /**
     * @param response
     * @param ex
     * @throws IOException
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    void handleMySQLRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.FORBIDDEN.value(),
                String.format(ERROR_MSG_FORMAT, ex.getMessage()));
    }


    @ExceptionHandler(ParseException.class)
    void handleParseRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.BAD_REQUEST.value(),
                String.format(ERROR_MSG_FORMAT, ex.getMessage()));
    }

    /**
     * @param response
     * @param ex
     * @throws IOException
     */
    @ExceptionHandler(Exception.class)
    void handleDefaultRequests(HttpServletResponse response, Exception ex) throws IOException {
        logger.error(ex.getMessage(), ex);
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                String.format(ERROR_MSG_FORMAT, ex.getMessage()));
    }
}
