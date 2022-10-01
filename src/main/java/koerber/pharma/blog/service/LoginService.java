package koerber.pharma.blog.service;

import koerber.pharma.blog.controller.exception.UserNotFoundException;
import koerber.pharma.blog.controller.exception.WrongPasswordException;
import koerber.pharma.blog.model.rest.LoginForm;
import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void loginUser(LoginForm loginForm){

        User storedLoginCredentials = findUserCredentialsByEmail(loginForm.getEmail());

        if(! storedLoginCredentials.getPassword().equals(loginForm.getPassword())){
            throw new WrongPasswordException(loginForm.getEmail());
        }
    }
    public User findUserCredentialsByEmail(String email){
        return  this.userRepository.findByEmail(email)
                                    .orElseThrow(() -> new UserNotFoundException(email));
    }

}
