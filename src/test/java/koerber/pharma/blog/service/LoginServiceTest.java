package koerber.pharma.blog.service;

import koerber.pharma.blog.controller.exception.UserNotFoundException;
import koerber.pharma.blog.controller.exception.WrongPasswordException;
import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.model.rest.LoginForm;
import koerber.pharma.blog.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class LoginServiceTest {

    private final UserRepository userRepository;
    private final LoginService loginService;
    private final EasyRandom generator;

    public LoginServiceTest(){
        generator = new EasyRandom();
        userRepository = Mockito.mock(UserRepository.class);
        loginService = new LoginService(userRepository);
    }

    @Test
    void findUserCredentialsByEmail_whenUserExistsInRepository_thenUserIsReturned(){
        User user = generator.nextObject(User.class);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        User retrievedUser = loginService.findUserCredentialsByEmail(user.getEmail());

        assertEquals(user, retrievedUser);
    }

    @Test
    void findUserCredentialsByEmail_whenNoUserFound_thenThrowsUserNotFoundException(){
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        String randomEmail = generator.nextObject(String.class);

        assertThrows(UserNotFoundException.class, () -> loginService.findUserCredentialsByEmail(randomEmail));
    }

    @Test
    void loginUser_whenUserNoExists_thenThrowsUserNotFoundException(){
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        LoginForm loginForm = generator.nextObject(LoginForm.class);

        assertThrows(UserNotFoundException.class, () -> loginService.loginUser(loginForm));
    }

    @Test
    void loginUser_whenUserPasswordIsWrong_thenThrowsWrongPasswordException(){
        User user = generator.nextObject(User.class);
        LoginForm loginForm = new LoginForm(user.getEmail(), generator.nextObject(String.class));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertThrows(WrongPasswordException.class, () -> loginService.loginUser(loginForm));
    }

    @Test
    void loginUser_whenLoginFormIsCorrect_thenNoExceptionIsThrown(){
        User user = generator.nextObject(User.class);
        LoginForm loginForm = new LoginForm(user.getEmail(), user.getPassword());

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        loginService.loginUser(loginForm);
        assertTrue(true);
    }
}
