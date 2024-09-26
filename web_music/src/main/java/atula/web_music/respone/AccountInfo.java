package atula.web_music.respone;

import atula.web_music.enetity.ROLE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfo {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private ROLE role=ROLE.ROLE_USER;
}
