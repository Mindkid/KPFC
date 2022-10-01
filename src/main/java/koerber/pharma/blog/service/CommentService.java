package koerber.pharma.blog.service;

import koerber.pharma.blog.model.entity.components.Comment;
import koerber.pharma.blog.repository.components.CommentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> retrieveComment(String title, String body, Pageable page){
        if(StringUtils.isNotEmpty(title)){
            return retrieveCommentContainingTitle(title, page);
        }

        if(StringUtils.isNotEmpty(body)){
            return retrieveCommentContainingBody(body, page);
        }

        return retrieveAllComment(page);
    }

    public List<Comment> retrieveCommentContainingTitle(String title, Pageable page){
        return this.commentRepository.findByTitleContaining(title, page);
    }

    public List<Comment> retrieveCommentContainingBody(String body, Pageable page){
        return this.commentRepository.findByBodyContaining(body, page);
    }

    public List<Comment> retrieveAllComment(Pageable page){
        return this.commentRepository.findAll(page)
                .get()
                .toList();
    }
}
