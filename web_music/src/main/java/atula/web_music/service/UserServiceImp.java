package atula.web_music.service;

import atula.web_music.config.JwtProvider;
import atula.web_music.dto.UserDTO;
import atula.web_music.enetity.Provider;
import atula.web_music.enetity.ROLE;
import atula.web_music.enetity.User;
import atula.web_music.repository.UserRepository;
import atula.web_music.request.LoginRequest;
import atula.web_music.respone.AccountInfo;
import atula.web_music.respone.LoginRespone;
import atula.web_music.respone.UserRespone;
import atula.web_music.utils.CodeAndMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static atula.web_music.utils.CodeAndMessage.*;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public UserRespone createUser(UserDTO userDTO) {
        if(userRepository.findByUsername(userDTO.getUsername())!=null){
            UserRespone userRespone = UserRespone.builder()
                    .code(CodeAndMessage.CREATE_ACCOUNT_CODE_FAIL)
                    .message(CodeAndMessage.CREATE_ACCOUNT_MESSSAGE_FAIL)
                    .accountInfo(null)
                    .build();
            return userRespone;
        }
        User newUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .provider(Provider.LOCAL)
                .roles(ROLE.ROLE_USER)
                .build();
        User saved = userRepository.save(newUser);



        return UserRespone.builder()
                .code(CREATE_ACCOUNT_CODE_SUCCESS)
                .message(CodeAndMessage.CREATE_ACCOUNT_MESSSAGE_SUCCESS)
                .accountInfo(AccountInfo.builder()
                        .firstName(saved.getFirstName())
                        .lastName(saved.getLastName())
                        .userName(saved.getUsername())
                        .password(saved.getPassword())
                        .role(ROLE.ROLE_USER)
                        .build())
                .build();
    }

    @Override
    public LoginRespone loginUser(LoginRequest loginRequest) throws Exception {
        if(userRepository.findByUsername(loginRequest.getUsername())==null){
          throw new Exception("User not found");
        }
        String jwt = null;
        String username = loginRequest.getUsername();
        String password = loginRequest.getUsername();
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(!username.equals(user.getUsername()) && !password.equals(user.getPassword())){
            throw new Exception("Username or password is wrong.");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        jwt = jwtProvider.generateToken(authentication);
        return LoginRespone.builder()
                .jwt(jwt)
                .message(CREATE_LOGIN_CODE_SUCCESS)
                .code(CREATE_LOGIN_MESSSAGE_SUCCESS)
                .build();
    }
    public void processOAuthPostLogin(String username) {
        User existUser = userRepository.findByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setProvider(Provider.GOOGLE);
//            newUser.setEnabled(true);

            userRepository.save(newUser);
        }

    }

    @Override
    public User findUserByJwt(String jwt) {
        String username = jwtProvider.getUserNameFromToken(jwt);
        User user = userRepository.findByUsername(username);
        return user;
    }


}
