package atula.web_music.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespone {
    private String code;
    private String message;
    private AccountInfo accountInfo;
}
