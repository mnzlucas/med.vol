package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get token from header bearer
        String authorizationHeader = getAuthorizationHeader(request);
        System.out.println("Authorization Header: " + authorizationHeader);


        filterChain.doFilter(request, response);
    }

    private static String getAuthorizationHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        //check if header is null throw runtime exception
        if (authorizationHeader == null ) {
            throw new RuntimeException("Token JWT header is missing");
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}
