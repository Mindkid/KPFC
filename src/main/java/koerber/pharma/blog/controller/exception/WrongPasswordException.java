package koerber.pharma.blog.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String email){
        super("Wrong password for " + email);
    }

}
