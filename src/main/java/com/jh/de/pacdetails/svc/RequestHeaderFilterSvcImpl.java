package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class RequestHeaderFilterSvcImpl extends OncePerRequestFilter {

    @Autowired
    private RequestHeaderBean requestHeaderBean;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        requestHeaderBean.setIsVirtual(Boolean.valueOf(request.getHeader("x-is-virtual")));
        filterChain.doFilter(request, response);
    }
}
