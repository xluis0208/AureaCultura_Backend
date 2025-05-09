package backend.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(InvalidActionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage invalidActionExceptionHandler(InvalidActionException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(KeyRepeatedDataException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessage keyRepeatedDataExceptionHandler(KeyRepeatedDataException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessage invalidDataExceptionHandler(InvalidDataException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }

    // Manejo genérico de excepciones no controladas
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );
    }
}
