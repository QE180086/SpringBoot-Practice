package atula.web_music.repository;

import atula.web_music.enetity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
public interface MusicRepository extends JpaRepository<Music,Long> {
    @Query("SELECT m FROM Music m where m.name LIKE %:keyword% OR m.auth LIKE %:keyword% OR m.type LIKE %:keyword%")
    public List<Music> findByName(@Param("keyword") String keyword);

    public Optional<Music> findById(Long id);




}
