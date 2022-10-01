package koerber.pharma.blog.repository;

import koerber.pharma.blog.model.entity.components.Post;
import koerber.pharma.blog.repository.components.ComponentRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class ComponentRepositoryTest {
    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 0;
    @Autowired
    private ComponentRepository<Post> repository;
    private final EasyRandom generator;

    private Post postTest;

    public ComponentRepositoryTest(){
        generator = new EasyRandom();
    }

    @BeforeEach
    void setUpTest(){
        postTest = generator.nextObject(Post.class);
        postTest.setUser(null);
        postTest.setPostComments(Collections.emptyList());
        repository.save(postTest);
    }

    @Test
    void findByTitleContaining_whenExistsPostContainingPartOfTheTitle_thenReturnThePost(){
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<Post> posts = repository.findByTitleContaining(postTest.getTitle(), pageToRetrieve);

        assertFalse(posts.isEmpty());
        assertEquals(postTest.getTitle(), posts.get(0).getTitle());
    }

    @Test
    void findByTitleContaining_whenNoPostWithPartOfTheTitle_thenEmptyList() {
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<Post> posts = repository.findByTitleContaining(generator.nextObject(String.class), pageToRetrieve);
        assertTrue(posts.isEmpty());
    }

    @Test
    void findByBodyContaining_whenExistsPostContainingPartOfTheBody_thenReturnThePost() {
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<Post> posts = repository.findByBodyContaining(postTest.getBody(), pageToRetrieve);

        assertFalse(posts.isEmpty());
        assertEquals(postTest.getBody(), posts.get(0).getBody());
    }

    @Test
    void findByBodyContaining_whenNoPostWithPartOfTheBody_thenEmptyList() {
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
        List<Post> posts = repository.findByBodyContaining(generator.nextObject(String.class), pageToRetrieve);
        assertTrue(posts.isEmpty());
    }

    @Test
    void findByBodyContaining_whenGettingPageThatNoExists_thenReturnEmptyList() {
        Pageable pageToRetrieve = PageRequest.of(FIRST_PAGE + 1, PAGE_SIZE);
        List<Post> posts = repository.findByBodyContaining(postTest.getBody(), pageToRetrieve);
        assertTrue(posts.isEmpty());
    }
}
