package org.residentialarea.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final String secretKey;

    public JwtFilter(String secretKey) {
        this.secretKey = secretKey;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUrl = getRequestUrl(request);
        log.info("User request: " + requestUrl);

        if (!requestUrl.contains("protected")) {
            log.info("Non protected API");
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.info("Invalid authorization");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid authorization");
            return;
        }

        String token = authorizationHeader.substring(7);
        try {
            log.info("secretKey: " + secretKey);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    null
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            log.error("error: ", e);
            log.info("Check token failed.");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getRequestUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        if (queryString != null) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }
}
