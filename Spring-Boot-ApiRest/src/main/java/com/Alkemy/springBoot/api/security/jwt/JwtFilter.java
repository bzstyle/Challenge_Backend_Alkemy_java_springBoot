package com.Alkemy.springBoot.api.security.jwt;

//Se ejecuta en cada peticion y por medio de jwtPovider si es valido permite el acceso.

import com.Alkemy.springBoot.api.security.service.UserDetailServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailServiceImp userDetailServiceImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {

            String token = getToken(request);
            System.out.println("TOKEN " + token);
            if (token != null && jwtProvider.validateToken(token)){
                System.out.println("point 1 ");
                String userName = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailServiceImp.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,null,
                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }catch (Exception e){
            LOGGER.error("Error  en el metodo doFilterInternal");
        }
            filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request){

        String header = request.getHeader("Authorization");
        if (header != null )
            if (header.startsWith("Bearer")) {
                return header.replace("Bearer ","");
            }else{
                return header;
            }
        return null;
    }
}
