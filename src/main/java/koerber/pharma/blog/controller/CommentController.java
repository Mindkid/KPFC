package koerber.pharma.blog.controller;

import koerber.pharma.blog.model.entity.components.Comment;
import koerber.pharma.blog.service.CommentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "comments", produces = "application/json")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> retrievePosts(@RequestParam(required = false) String title,
                                       @RequestParam(required = false) String body,
                                       @RequestParam(required = false, defaultValue = "0") int pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") int pageSize){

        Pageable pageToRetrieve = PageRequest.of(pageIndex, pageSize);

        return this.commentService.retrieveComment(title, body, pageToRetrieve);
    }

}
