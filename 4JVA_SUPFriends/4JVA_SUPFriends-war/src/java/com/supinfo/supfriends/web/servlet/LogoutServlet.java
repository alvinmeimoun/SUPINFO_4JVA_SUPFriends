/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.servlet;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet ayant pour fonctionnalités de delogger l'utilisateur
 */
public class LogoutServlet extends HttpServlet {
    /**
     * Déconnecte l'utilisateur
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");
        request.getSession().removeAttribute("id");
        response.sendRedirect("http://localhost:8080/4JVA_SUPFriends-war/faces/index.xhtml");
    }

    /**
     * Returns a short description of the servle    t.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Logout servlet";
    }

}
