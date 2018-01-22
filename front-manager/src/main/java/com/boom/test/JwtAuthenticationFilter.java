package com.boom.test;


import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Intellij IDEA
 *
 * @Author summer
 * @Date 2018/1/22 下午10:37
 * @Description
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String protectUrlPattern;
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    public JwtAuthenticationFilter(String protectUrlPattern) {
        this.protectUrlPattern = protectUrlPattern;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            if(pathMatcher.match(protectUrlPattern, request.getServletPath())) {
                String token = request.getHeader(JwtUtil.HEADER_STRING);
                JwtUtil.verify(token);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);

    }
}
