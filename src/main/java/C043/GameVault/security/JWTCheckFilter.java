package C043.GameVault.security;

import C043.GameVault.entities.User;
import C043.GameVault.exceptions.UnauthorizedException;
import C043.GameVault.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Token not found");
        String accessToken = authHeader.substring(7);
        jwtTools.verifyToken(accessToken);
        String id = jwtTools.extractIdFromToken(accessToken);
        User currentUser = this.userService.findById(Integer.parseInt(id));
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
