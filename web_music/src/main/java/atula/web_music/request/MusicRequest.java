package atula.web_music.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MusicRequest {
    private String name;
    private String type;
    private String url;
    private String auth;
    private String description;

}
