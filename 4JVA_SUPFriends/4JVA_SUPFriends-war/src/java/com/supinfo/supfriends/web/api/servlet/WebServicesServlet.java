/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.api.servlet;

import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alvin
 */
public class WebServicesServlet extends HttpServlet {

    @EJB
    UserFacade userFacade;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String responseJson;
        
        switch(action){
            case "update":
                String latitude, longitude;
                latitude = req.getParameter("latitude");
                longitude = req.getParameter("longitude");
                
                UserEntity user = userFacade.findByUsername(username);
                
                break;
        }
    }
    
    
    
}
