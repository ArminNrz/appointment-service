package com.appoitment.userservice.security.filter;

import com.appoitment.userservice.security.SecurityService;
import com.appoitment.userservice.security.data.AuthorizationData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@AllArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final SecurityService utility;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (!request.getServletPath().equals("/api/login") && !request.getServletPath().equals("/api/token/refresh")) {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            authorizeRequest(authorizationHeader, response);
        }
        filterChain.doFilter(request, response);
    }

    private void authorizeRequest(String authorizationHeader, HttpServletResponse response) throws IOException {

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String token = authorizationHeader.substring("Bearer ".length());

                AuthorizationData authorizationData = utility.extractAuthorizationData(token);
                String username = authorizationData.getUsername();
                String[] roles = authorizationData.getRoles();

                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            catch (Exception exception) {
                utility.handleUnAuthorizeException(exception, response);
            }
        }
    }

}
