package koerber.pharma.blog.repository.components;

import koerber.pharma.blog.model.entity.components.BaseComponent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface ComponentRepository<K extends BaseComponent> extends PagingAndSortingRepository<K, Integer> {
    List<K> findByTitleContaining(String title, Pageable pageable);
    List<K> findByBodyContaining(String body, Pageable pageable);
}


