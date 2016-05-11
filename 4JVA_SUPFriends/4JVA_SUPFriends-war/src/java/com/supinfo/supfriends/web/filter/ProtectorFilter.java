/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.filter;

import com.supinfo.supfriends.ejb.config.ServerConfig;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Sécurise les Servlets auquels il est appliqué La vérification de sécurité se
 * fait par Cookies
 */
public class ProtectorFilter implements Filter {

    @EJB
    UserFacade userFacade;

    public ProtectorFilter() {
        if (userFacade == null) {
            userFacade = new UserFacade();
        }
    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean validCredentials = false;

        String path = ((HttpServletRequest) request).getRequestURI();
        if (isFullyAuthorizedUrl(path)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("id") != null && session.getAttribute("username") != null
                && session.getAttribute("password") != null) {
            UserEntity user = userFacade.find((Long) session.getAttribute("id"));
            if (user != null) {
                if (DigestUtils.sha256Hex(user.getPassword()).equals(session.getAttribute("password"))) {
                    validCredentials = true;
                }
            }
        }

        if (validCredentials) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(ServerConfig.CONTEXT_URL + "/faces/login.xhtml");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private boolean isFullyAuthorizedUrl(String url) {
        if (url.startsWith(ServerConfig.CONTEXT_URL + "/faces/login.xhtml")) {
            return true;
        } else if (url.startsWith(ServerConfig.CONTEXT_URL + "/faces/register.xhtml")) {
            return true;
        } else if (url.startsWith(ServerConfig.CONTEXT_URL + "/faces/index.xhtml")) {
            return true;
        } else if (url.startsWith(ServerConfig.CONTEXT_URL + "/faces/css")) {
            return true;
        } else if (url.startsWith(ServerConfig.CONTEXT_URL + "/faces/js")) {
            return true;
        } else if (url.equals(ServerConfig.CONTEXT_URL + "/faces/")) {
            return true;
        } else if (url.equals(ServerConfig.CONTEXT_URL + "/")) {
            return true;
        } else {
            return false;
        }
    }

}
