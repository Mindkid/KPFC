package koerber.pharma.blog.service;

import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.model.entity.components.Post;
import koerber.pharma.blog.model.rest.PostRequest;
import koerber.pharma.blog.repository.components.PostRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository,
                       UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> retrievePost(String title, String body, Pageable page){
        if(StringUtils.isNotEmpty(title)){
            return retrievePostContainingTitle(title, page);
        }

        if(StringUtils.isNotEmpty(body)){
            return retrievePostContainingBody(body, page);
        }

        return retrieveAllPost(page);
    }

    public List<Post> retrievePostContainingTitle(String title, Pageable page){
        return this.postRepository.findByTitleContaining(title, page);
    }

    public List<Post> retrievePostContainingBody(String body, Pageable page){
        return this.postRepository.findByBodyContaining(body, page);
    }

    public List<Post> retrieveAllPost(Pageable page){
        return this.postRepository.findAll(page)
                .get()
                .toList();
    }

    public Post savePost(PostRequest postRequest){
        User user = userService.findUserByEmail(postRequest.getUserEmail());
        Post post = new Post();
        post.setBody(postRequest.getBody());
        post.setTitle(postRequest.getTitle());
        post.setUser(user);
        this.postRepository.save(post);
        return post;
    }
}
