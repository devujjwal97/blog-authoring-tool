package blogauthoringtool.blog.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogauthoringtool.blog.Model.blog;

@Repository
public interface PostRepository extends JpaRepository<blog, Long> {
}
