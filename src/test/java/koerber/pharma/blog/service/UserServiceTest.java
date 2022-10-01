package koerber.pharma.blog.service;

import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.repository.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {
    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 0;
    private static final int COLLECTION_USERS_SIZE = 5;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EasyRandom generator;

    private List<User> users;
    private Pageable pageToRetrieve;

    public UserServiceTest(){
        generator = new EasyRandom();
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @BeforeEach
    void setUp(){
        users = generator.objects(User.class, COLLECTION_USERS_SIZE).toList();
        pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
    }

    @Test
    void retrieveAllUsers_whenUsersExistsInTheRepository_thenUserAreRetrieved(){
        when(userRepository.findAll(pageToRetrieve)).thenReturn(new PageImpl<>(users));
        List<User> retrievedUsers = userService.retrieveAllUsers(pageToRetrieve);
        assertEquals(users, retrievedUsers);
    }

    @Test
    void retrieveUsersContainingEmail_whenQueryingOfPartOfEmail_thenAllUserThatHavePartOfEmailAreReturn(){
        String usersNewEmail = generator.nextObject(String.class);
        users.forEach(user -> user.setEmail(usersNewEmail));

        when(userRepository.findByEmailContaining(usersNewEmail, pageToRetrieve)).thenReturn(users);

        List<User> retrievedUsers = userService.retrieveUsersContainingEmail(usersNewEmail, pageToRetrieve);

        assertEquals(users, retrievedUsers);
    }

    @Test
    void retrieveUsers_whenQueryingForName_thenFindUsersContainingName(){
        String usersNewName = generator.nextObject(String.class);
        users.forEach(user -> user.setName(usersNewName));

        when(userRepository.findByNameContaining(usersNewName, pageToRetrieve)).thenReturn(users);

        List<User> retrievedUsers = userService.retrieveUsers(usersNewName, null, pageToRetrieve);
        assertEquals(users, retrievedUsers);
    }

    @Test
    void retrieveUsers_whenQueryingForEmail_thenFindUsersContainingEmail(){
        String usersNewEmail = generator.nextObject(String.class);
        users.forEach(user -> user.setEmail(usersNewEmail));

        when(userRepository.findByEmailContaining(usersNewEmail, pageToRetrieve)).thenReturn(users);

        List<User> retrievedUsers = userService.retrieveUsers(null, usersNewEmail, pageToRetrieve);
        assertEquals(users, retrievedUsers);
    }

    @Test
    void retrieveUsers_whenNoParameterIsSet_thenFindAllUsers(){
        when(userRepository.findAll(pageToRetrieve)).thenReturn(new PageImpl<>(users));
        List<User> retrievedUsers = userService.retrieveUsers(null, null, pageToRetrieve);
        assertEquals(users, retrievedUsers);
    }

}
