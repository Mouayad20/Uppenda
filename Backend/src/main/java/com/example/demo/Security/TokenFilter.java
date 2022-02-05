package com.example.demo.Security;

import com.example.demo.Converters.UserConverter;
import com.example.demo.Models.SignInModel;
import com.example.demo.Models.UserModel;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private TokenUtil tokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        tokenUtil = new TokenUtil();

        final String header = request.getHeader("Authorization");

        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (header != null && securityContext.getAuthentication() == null) {

            System.out.println("\n\t This token is not authenticated yet\n");

            String token = header.substring("Bearer ".length());

            String email = tokenUtil.getEmailFromToken(token);

            if (email != null) {

                System.out.println("\n\t This mobile is true  \n");

                UserModel user = userConverter.getUserModelWithBasicInformation(userRepository.findByEmail(email).get());

                UserDetails userDetails = new SignInModel(user.getMobile(), user.getPassword());

                if (tokenUtil.isValid(token, userDetails)) {

                    System.out.println("\n\t\t  this token is not expired \n");

                    UsernamePasswordAuthenticationToken authenticatedToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

                    authenticatedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticatedToken);

                } else
                    System.out.println("\n\t\t  this token is expired \n");
            } else
                System.out.println("\n\t\t  this mobile is false \n");
        } else
            System.out.println("\n\t\t  This token is authenticated \n");

        filterChain.doFilter(request, response);

    }
}
