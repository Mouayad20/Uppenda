package com.example.demo.Configurations;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Models.MyUserDetails;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        tokenUtil = new TokenUtil();

        final String header = request.getHeader("Authorization");

        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (header != null && securityContext.getAuthentication() == null) {

            String token = header.substring("Bearer ".length());

            String userName = tokenUtil.getUsernameFromToken(token);

            if (userName != null) {

                UserEntity userrr = userRepository.findByEmail(userName).get();

                UserDetails userDetails = new MyUserDetails(userrr.getEmail(), userrr.getPassword());

                if (tokenUtil.isValied(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                } else
                    System.out.println("\n\t\tISSSSS NOOOOOOT VALLLLLLL");
            } else
                System.out.println("zzzzzzzzzzzzzzzzzzzzzz");
        } else
            System.out.println("\n\n\t\t\tpopopopopoppopopo\n\n");
            
        filterChain.doFilter(request, response);
    }
}
