/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Sécurise les Servlets auquels il est appliqué
 * La vérification de sécurité se fait par Cookies
 */
public class ProtectorFilter implements Filter {

    public ProtectorFilter() {
    }

    public void destroy() {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        List<Cookie> cookies;
        if(req.getCookies() != null){
            cookies = Arrays.asList(req.getCookies());
        } else {
            cookies = new ArrayList<>();
        }
        
        boolean validCredentials = false;
        String username = "";
        String token = "";
        String id = "";
        
        for(Cookie c : cookies){
            if("sb_username".equals(c.getName())){
                username = c.getValue();
            }
            if("sb_token".equals(c.getName())){
                token = c.getValue();
            }
            if("sb_id".equals(c.getName())){
                id = c.getValue();
            }
        }
        
        if(username != null && username.length() > 0 
                && DigestUtils.sha256Hex(username).equals(token)){
            validCredentials = true;
        }
        
        
        if (validCredentials) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendRedirect("home");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }


}
