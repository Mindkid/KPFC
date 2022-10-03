package koerber.pharma.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.model.rest.LoginForm;
import koerber.pharma.blog.repository.UserRepository;
import koerber.pharma.blog.service.LoginService;
import koerber.pharma.blog.service.UserService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoginControllerTest {
    private static final String LOGIN_URI = "/login";

    private final EasyRandom generator;
    private final MockMvc mvc;
    private final UserRepository userRepository;
    private final UserService userService;

    public LoginControllerTest() {
        generator = new EasyRandom();
        userRepository = Mockito.mock(UserRepository.class);;
        userService = new UserService(userRepository);
        LoginService loginService = new LoginService(userService);
        LoginController controller = new LoginController(loginService);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void performPostRequest_whenNoRequestBody_thenIsBadRequest() throws Exception {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post(LOGIN_URI)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andReturn();
    }

    @Test
    void performPostRequest_whenWrongEmailIsSent_thenUserNotFound() throws Exception {
        LoginForm loginForm = generator.nextObject(LoginForm.class);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.post(LOGIN_URI)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(new ObjectMapper().writeValueAsString(loginForm))
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound())
            .andReturn();
    }

    @Test
    void performPostRequest_whenWrongPasswordIsSent_thenUserIsUnauthorized() throws Exception {
        LoginForm loginForm = generator.nextObject(LoginForm.class);
        User user = generator.nextObject(User.class);
        user.setEmail(loginForm.getEmail());

        when(userRepository.findByEmail(loginForm.getEmail())).thenReturn(Optional.of(user));
        mvc.perform(MockMvcRequestBuilders.post(LOGIN_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(loginForm))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
