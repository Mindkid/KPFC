package koerber.pharma.blog.repository;

import koerber.pharma.blog.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    List<User> findByEmailContaining(String email, Pageable pageable);
    List<User> findByNameContaining(String name, Pageable pageable);
    Optional<User> findByEmail(String email);
}


