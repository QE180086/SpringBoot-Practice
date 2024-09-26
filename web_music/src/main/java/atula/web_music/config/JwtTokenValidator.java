package atula.web_music.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;
import java.util.prefs.BackingStoreException;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt =request.getHeader(JwtConstant.JWT_HEADER);

        if(jwt!=null){
            jwt = jwt.substring(7); //Bearer Token :))

            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                // su dung thu vien jwt de lay thong tin va giai ma token
                // Xac thuc va lay claim token
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String username = String.valueOf(claims.get("username")); // lay gia tri cua truong username tu claim
                String authorities = String.valueOf((claims.get("authorities"))); // lay gia tri cua truong authorities( quyen nguoi dung) tu claim

                // ROLE_CUSTOMER, ROLE_ADMIN

                // chuyen doi chuoi cac quyen sang doi tuong GrantedAuthority ma spring security use to show quyen of user
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                // tao doi tuong moi bang cach su dung email nhung k can password(xac thuc dua tren jwt)
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication); // nguoi dung da duoc xac thuc



            } catch (Exception e){
                throw new BadCredentialsException("invalid token.....");
            }

        }
        filterChain.doFilter(request, response); // tiep tuc filter khac

    }
}
