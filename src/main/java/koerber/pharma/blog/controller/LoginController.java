package koerber.pharma.blog.controller;

import koerber.pharma.blog.model.rest.LoginForm;
import koerber.pharma.blog.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "login", consumes = "application/json",  produces = "application/json")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }


    @PostMapping
    public void login(@Valid @RequestBody LoginForm loginForm){
        loginService.loginUser(loginForm);
    }

}
