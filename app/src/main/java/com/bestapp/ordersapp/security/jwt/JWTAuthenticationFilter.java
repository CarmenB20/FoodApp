package com.bestapp.ordersapp.security.jwt;

import com.bestapp.ordersapp.authentication.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private JWTokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwt = tokenProvider.getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {

                String userEmail  = tokenProvider.getUserEmailFromJWT(jwt);
                UserDetails userDetails = appUserDetailsService.loadUserByUsername(userEmail);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e){
            System.out.println("ERROR " + "Could not set user authentication in security context" + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
