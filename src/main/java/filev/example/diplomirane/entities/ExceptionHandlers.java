package filev.example.diplomirane.entities;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    protected String handleException(Exception exception, Model model){
        model.addAttribute("message", exception.getMessage());
        return "/errors/errors";
    }


}
