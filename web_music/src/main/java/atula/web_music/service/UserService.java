package atula.web_music.service;

import atula.web_music.dto.UserDTO;
import atula.web_music.enetity.User;
import atula.web_music.request.LoginRequest;
import atula.web_music.respone.LoginRespone;
import atula.web_music.respone.UserRespone;

public interface UserService {
    public UserRespone createUser(UserDTO userDTO);
    public LoginRespone loginUser(LoginRequest loginRequest) throws Exception;
    public User findUserByJwt(String jwt);


}
