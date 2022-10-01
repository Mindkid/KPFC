package koerber.pharma.blog.service;

import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.model.entity.components.Post;
import koerber.pharma.blog.repository.components.PostRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PostServiceTest {
    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 0;
    private static final int COLLECTION_POST_SIZE = 5;
    private final EasyRandom generator;

    private final PostRepository repository;
    private final PostService service;

    private List<Post> posts;
    private Pageable pageToRetrieve;

    public PostServiceTest(){
        generator = new EasyRandom();
        repository = Mockito.mock(PostRepository.class);
        service = new PostService(repository);
    }


    @BeforeEach
    void setUp(){
        posts = generator.objects(Post.class, COLLECTION_POST_SIZE).toList();
        pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
    }

    @Test
    void retrievePost_whenQueryingForTitle_thenReturnPostsWithTheTitle(){
        String postNewTitle = generator.nextObject(String.class);
        posts.forEach(post -> post.setTitle(postNewTitle));

        when(repository.findByTitleContaining(postNewTitle, pageToRetrieve)).thenReturn(posts);

        List<Post> retrievedPost =  service.retrievePost(postNewTitle, null, pageToRetrieve);

        assertEquals(posts, retrievedPost);
    }

    @Test
    void retrievePost_whenQueryingForBody_thenReturnPostsWithTheBody(){
        String postNewBody= generator.nextObject(String.class);
        posts.forEach(post -> post.setBody(postNewBody));

        when(repository.findByBodyContaining(postNewBody, pageToRetrieve)).thenReturn(posts);

        List<Post> retrievedPost =  service.retrievePost(null, postNewBody, pageToRetrieve);

        assertEquals(posts, retrievedPost);
    }

    @Test
    void retrievePost_whenNoParamsAreSet_thenReturnAllThePosts(){
        when(repository.findAll(pageToRetrieve)).thenReturn(new PageImpl<>(posts));
        List<Post> retrievedPost =  service.retrievePost(null, null, pageToRetrieve);
        assertEquals(posts, retrievedPost);
    }
}
