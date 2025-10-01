package com.br.checklist.checklist.infra.errors;

import com.br.checklist.checklist.exceptions.ExpiredRefreshToken;
import com.br.checklist.checklist.exceptions.ObjectNotFound;
import com.br.checklist.checklist.exceptions.UnauthorizedUser;
import com.br.checklist.checklist.exceptions.UserAlreadyRegistered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnauthorizedUser.class)
    private ResponseEntity<RestErrorMessage> unauthorizedUser(UnauthorizedUser exception) {
        RestErrorMessage threadErrorMessage = new RestErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(threadErrorMessage);
    }

    @ExceptionHandler(UserAlreadyRegistered.class)
    private ResponseEntity<RestErrorMessage> userAlreadyRegistered(UserAlreadyRegistered exception) {
        RestErrorMessage threadErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threadErrorMessage);
    }

    @ExceptionHandler(ExpiredRefreshToken.class)
    private ResponseEntity<RestErrorMessage> expiredRefreshToken(ExpiredRefreshToken exception) {
        RestErrorMessage threadErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threadErrorMessage);
    }

    @ExceptionHandler(ObjectNotFound.class)
    private ResponseEntity<RestErrorMessage> objectNotFound(ObjectNotFound exception) {
        RestErrorMessage threadErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threadErrorMessage);
    }

}
