package atula.web_music.repository;

import atula.web_music.enetity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

public interface UserRepository extends JpaRepository<User,Long> {
public User findByUsername(String username);
}
