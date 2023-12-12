package filev.example.diplomirane.entities.exception;

import org.springframework.format.annotation.DateTimeFormat;

public class ExceptionDetails extends RuntimeException{

    private DateTimeFormat dateTimeFormat;

    private String message;

    public ExceptionDetails(String message){
        super(message);
    }

}
