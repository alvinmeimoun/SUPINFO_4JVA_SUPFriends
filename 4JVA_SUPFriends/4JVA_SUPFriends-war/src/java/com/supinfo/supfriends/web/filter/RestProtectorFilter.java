/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.filter;

import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Sécurise les Servlets auquels il est appliqué
 * La vérification de sécurité se fait par Headers
 * Destiné à être utilisé sur les API REST sécurisés
 */
public class RestProtectorFilter implements Filter {

    @EJB
    UserFacade userFacade;
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        boolean authSuccess = false;
        
        if(httpRequest.getHeader("AUTH_USERNAME") != null && 
                httpRequest.getHeader("AUTH_PASSWORD") != null){
            String username = httpRequest.getHeader("AUTH_USERNAME");
            String passwordCrypted = DigestUtils.sha256Hex(httpRequest.getHeader("AUTH_PASSWORD"));
        
            UserEntity user = userFacade.findByUsername(username);
            
            if(user != null && user.getPassword().equals(passwordCrypted)) authSuccess = true;
        }
        
        
        if(authSuccess){
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(403);
            httpResponse.getWriter().print("Not authenticated");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }

    @Override
    public void destroy() {
    
    }

    
}