package C043.PowerUp.security;

import C043.PowerUp.entities.User;
import C043.PowerUp.exceptions.UnauthorizedException;
import C043.PowerUp.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {
    private final List<AntPathRequestMatcher> excludeList =
            Arrays.asList(AntPathRequestMatcher.antMatcher("/auth/**"),
                    AntPathRequestMatcher.antMatcher("/games/**"));
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Token not found");
        String accessToken = authHeader.substring(7);
        jwtTools.verifyToken(accessToken);
        String id = jwtTools.extractIdFromToken(accessToken);
        User currentUser = this.userService.findById(Integer.parseInt(id));
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(currentUser, null,
                        null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        return this.excludeList.stream()
                .anyMatch(matcher -> matcher.matches(request));
    }
}
