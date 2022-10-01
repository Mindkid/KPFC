package koerber.pharma.blog.controller;

import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users", produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> retrieveUsers(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false, defaultValue = "0") int pageIndex,
                                    @RequestParam(required = false, defaultValue = "10") int pageSize){

        Pageable pageToRetrieve = PageRequest.of(pageIndex, pageSize);

        return this.userService.retrieveUsers(name, email, pageToRetrieve);
    }

}
