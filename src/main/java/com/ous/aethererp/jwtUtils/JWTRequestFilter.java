package com.ous.aethererp.jwtUtils;

import com.ous.aethererp.security.AppUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final AppUserDetailService appUserDetail;
    private final JWTUtils jwtUtils;


    private static final List<String> PUBLIC_URLS = List.of("/register", "/login",
            "/send-reset-otp", "/reset-password", "/logout");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        if (PUBLIC_URLS.contains(path)){
            filterChain.doFilter(request, response);
            return;
        }

        String email = null;
        String jwt = null;

        // 1. Check the authorization header
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
        }

        // 2. If not found in header, check cookies
        if (jwt == null){
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie cookie: cookies){
                    if ("jwt".equals(cookie.getName())){
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
        }

        // 3. Validate the token and set security context

        if (jwt != null) {
            email = jwtUtils.extractEmail(jwt);
            if (email !=null && SecurityContextHolder.getContext().getAuthentication() == null){
               UserDetails userDetails = appUserDetail.loadUserByUsername(email);
               if (jwtUtils.validateToken(jwt, userDetails)){
                   UsernamePasswordAuthenticationToken authToken =
                           new UsernamePasswordAuthenticationToken(userDetails,
                                   null, userDetails.getAuthorities());
                   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authToken);
               }
            }
        }
        filterChain.doFilter(request, response);
    }
}
