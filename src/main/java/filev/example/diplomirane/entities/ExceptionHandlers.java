package filev.example.diplomirane.entities;

import filev.example.diplomirane.entities.exception.ApiException;
import filev.example.diplomirane.entities.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;


//to catch custom exceptions that may occur and modify them
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    protected String handleException(Exception exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/errors/errors";
    }

    @ExceptionHandler(value = {ExceptionDetails.class})
    public ResponseEntity<Object> handleExceptionDetails(ExceptionDetails e){
        //payload containing exception details
        ApiException apiException =  new ApiException(e.getMessage(),e, HttpStatus.BAD_GATEWAY, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }


}
