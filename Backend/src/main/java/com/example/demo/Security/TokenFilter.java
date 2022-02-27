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

            logger.error("This token is not authenticated yet");

            String token = header.substring("Bearer ".length());

            String email = tokenUtil.getEmailFromToken(token);

            if (email != null) {

                logger.info("This email is true ");

                UserModel user = userConverter.getUserModelWithBasicInformation(userRepository.findByEmail(email).get());

                UserDetails userDetails = new SignInModel(user.getEmail(), user.getPassword());

                if (tokenUtil.isValid(token, userDetails)) {

                    logger.info("This token is not expired ");

                    UsernamePasswordAuthenticationToken authenticatedToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

                    authenticatedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticatedToken);

                } else
                    logger.error("This token is not valid ");
            } else
                logger.error("This email is false ");
        } else
            logger.error("This token is not allowed ");

        filterChain.doFilter(request, response);

    }
}
