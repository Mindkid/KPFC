package koerber.pharma.blog.service;

import koerber.pharma.blog.controller.exception.WrongPasswordException;
import koerber.pharma.blog.model.rest.LoginForm;
import koerber.pharma.blog.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    private final UserService userService;

    public LoginService(UserService userService){
        this.userService = userService;
    }
    public void loginUser(LoginForm loginForm){

        User storedLoginCredentials = userService.findUserByEmail(loginForm.getEmail());

        if(! storedLoginCredentials.getPassword().equals(loginForm.getPassword())){
            throw new WrongPasswordException(loginForm.getEmail());
        }
    }

}
