package atula.web_music.enetity;

import atula.web_music.dto.MusicFavourtitesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private ROLE roles;
    @ElementCollection

    private List<Music> listFavourites;


    @Enumerated(EnumType.STRING)
    private Provider provider;
    @CreationTimestamp
    private LocalDateTime create_At;

}
