package com.jxt.jxtbank.exceptions;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxt.jxtbank.res.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver handlerExceptionResolver;


    private final ObjectMapper objectMapper;


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        Response<?> errorResponse = Response.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(authException.getMessage())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));




    }
}
