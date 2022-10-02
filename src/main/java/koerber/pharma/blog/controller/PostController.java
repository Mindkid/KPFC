package koerber.pharma.blog.controller;

import koerber.pharma.blog.model.entity.components.Post;
import koerber.pharma.blog.model.rest.PostRequest;
import koerber.pharma.blog.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "post", produces = "application/json")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<Post> retrievePosts(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String body,
                              @RequestParam(required = false, defaultValue = "0") int pageIndex,
                              @RequestParam(required = false, defaultValue = "10") int pageSize){

        Pageable pageToRetrieve = PageRequest.of(pageIndex, pageSize);

        return this.postService.retrievePost(title, body, pageToRetrieve);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(PostRequest post){
        return this.postService.savePost(post);
    }

}
