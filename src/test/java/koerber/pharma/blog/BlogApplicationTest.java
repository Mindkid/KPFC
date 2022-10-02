package koerber.pharma.blog;

import koerber.pharma.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class BlogApplicationTest {

    @Autowired
    private UserService user;
    @Test
    void loadContext(){
        assertThat(user).isNotNull();
    }
}
