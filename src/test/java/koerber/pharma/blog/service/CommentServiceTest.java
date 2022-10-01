package koerber.pharma.blog.service;

import koerber.pharma.blog.model.entity.components.Comment;
import koerber.pharma.blog.model.entity.components.Post;
import koerber.pharma.blog.repository.components.CommentRepository;
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

class CommentServiceTest {
    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 0;
    private static final int COLLECTION_COMMENT_SIZE = 5;
    private final EasyRandom generator;

    private final CommentRepository repository;
    private final CommentService service;

    private List<Comment> comments;
    private Pageable pageToRetrieve;

    public CommentServiceTest(){
        generator = new EasyRandom();
        repository = Mockito.mock(CommentRepository.class);
        service = new CommentService(repository);
    }


    @BeforeEach
    void setUp(){
        comments = generator.objects(Comment.class, COLLECTION_COMMENT_SIZE).toList();
        pageToRetrieve = PageRequest.of(FIRST_PAGE, PAGE_SIZE);
    }

    @Test
    void retrieveComment_whenQueryingForTitle_thenReturnCommentsWithTheTitle(){
        String commentNewTitle = generator.nextObject(String.class);
        comments.forEach(comment -> comment.setTitle(commentNewTitle));

        when(repository.findByTitleContaining(commentNewTitle, pageToRetrieve)).thenReturn(comments);

        List<Comment> retrievedComment =  service.retrieveComment(commentNewTitle, null, pageToRetrieve);

        assertEquals(comments, retrievedComment);
    }

    @Test
    void retrieveComment_whenQueryingForBody_thenReturnCommentsWithTheBody(){
        String postNewBody= generator.nextObject(String.class);
        comments.forEach(comment -> comment.setBody(postNewBody));

        when(repository.findByBodyContaining(postNewBody, pageToRetrieve)).thenReturn(comments);

        List<Comment> retrievedPost =  service.retrieveComment(null, postNewBody, pageToRetrieve);

        assertEquals(comments, retrievedPost);
    }

    @Test
    void retrieveComment_whenNoParamsAreSet_thenReturnAllTheComments(){
        when(repository.findAll(pageToRetrieve)).thenReturn(new PageImpl<>(comments));
        List<Comment> retrievedPost =  service.retrieveComment(null, null, pageToRetrieve);
        assertEquals(comments, retrievedPost);
    }
}
