package koerber.pharma.blog.repository;

import koerber.pharma.blog.model.entity.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 0;
    @Autowired
    private UserRepository repository;
    private final EasyRandom generator;

    private User userTest;

    public UserRepositoryTest(){
        generator = new EasyRandom();
    }

    @BeforeEach
    void setUpTest(){
        userTest = generator.nextObject(User.class);
        userTest.setUserPosts(Collections.emptyList());
        repository.save(userTest);
    }

    @Test
    void contextLoads() {
        assertNotNull(repository);
    }

    @Test
    void findByEmail_whenEmailExists_thenReturnsUser(){
        Optional<User> retrievedUser = repository.findByEmail(userTest.getEmail());

        assertTrue(retrievedUser.isPresent());
        assertEquals(userTest.getEmail(), retrievedUser.get().getEmail());
    }

    @Test
    void findByEmail_whenNoUserWithEmail_thenReturnEmptyOptional(){
        Optional<User> retrievedUser = repository.findByEmail(generator.nextObject(String.class));
        assertTrue(retrievedUser.isEmpty());
    }

    @Test
    void findByEmailContaining_whenExistUserWithPartOfTheEmail_thenReturnsTheGivenUser(){
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<User> users = repository.findByEmailContaining(userTest.getEmail(), pageToRetrieve);

        assertFalse(users.isEmpty());
        assertEquals(userTest.getEmail(), users.get(0).getEmail());
    }

    @Test
    void findByEmailContaining_whenNoUserWithPartOfTheEmail_thenReturnsEmptyList(){
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<User> users = repository.findByEmailContaining(generator.nextObject(String.class), pageToRetrieve);

        assertTrue(users.isEmpty());
    }

    @Test
    void findByEmailContaining_whenRequestingNoExistingPage_thenReturnsEmptyList(){
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE + 1, PAGE_SIZE);
        List<User> users = repository.findByEmailContaining(userTest.getEmail(), pageToRetrieve);

        assertTrue(users.isEmpty());
    }

    @Test
    void findByNameContaining_whenExistUserWithPartOfTheName_thenReturnsTheUser(){
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<User> users = repository.findByNameContaining(userTest.getName(), pageToRetrieve);

        assertFalse(users.isEmpty());
        assertEquals(userTest.getName(), users.get(0).getName());
    }

    @Test
    void findByNameContaining_whenNoUserWithPartOfTheName_thenReturnsEmptyList(){
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<User> users = repository.findByNameContaining(generator.nextObject(String.class), pageToRetrieve);

        assertTrue(users.isEmpty());
    }
}
