package koerber.pharma.blog.service;

import koerber.pharma.blog.model.entity.User;
import koerber.pharma.blog.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> retrieveUsers(String name, String email, Pageable page){

        if(StringUtils.isNotEmpty(name)){
            return retrieveUsersContainingName(name, page);
        }

        if(StringUtils.isNotEmpty(email)){
            return retrieveUsersContainingEmail(email, page);
        }

        return retrieveAllUsers(page);
    }

    public List<User> retrieveUsersContainingName(String name, Pageable page){
        return this.userRepository.findByNameContaining(name, page);
    }

    public List<User> retrieveUsersContainingEmail(String email, Pageable page){
        return this.userRepository.findByEmailContaining(email, page);
    }

    public List<User> retrieveAllUsers(Pageable page){
        return this.userRepository.findAll(page)
                .get()
                .toList();
    }
}
