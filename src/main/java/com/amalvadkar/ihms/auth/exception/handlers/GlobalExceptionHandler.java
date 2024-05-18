package com.amalvadkar.ihms.auth.exception.handlers;

import com.amalvadkar.ihms.common.exceptions.AppException;
import com.amalvadkar.ihms.common.exceptions.ResourceNotFoundException;
import com.amalvadkar.ihms.common.models.response.CustomResModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.amalvadkar.ihms.auth.utils.AppConstants.SOMETHING_WENT_WRONG_ERROR_MESSAGE;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static void logException(Exception ex) {
        log.error("Exception occurred -> ", ex);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public CustomResModel handleResourceNotFoundException(ResourceNotFoundException ex) {
        logException(ex);
        List<String> errorMessages = List.of(ex.getMessage());
        return CustomResModel.fail(errorMessages, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppException.class)
    public CustomResModel handleAppException(AppException ex) {
        logException(ex);
        List<String> errorMessages = List.of(ex.getMessage());
        return CustomResModel.fail(errorMessages, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public CustomResModel handleException(Exception ex) {
        logException(ex);
        List<String> errorMessages = List.of(SOMETHING_WENT_WRONG_ERROR_MESSAGE);
        return CustomResModel.fail(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
