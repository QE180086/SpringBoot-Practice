package atula.web_music.controller;

import atula.web_music.dto.UserDTO;
import atula.web_music.request.LoginRequest;
import atula.web_music.respone.LoginRespone;
import atula.web_music.respone.UserRespone;
import atula.web_music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public UserRespone createAccount(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GetMapping("/loginLocal")
    public LoginRespone login(@RequestBody LoginRequest loginRequest) throws Exception {
        return userService.loginUser(loginRequest);
    }


}
