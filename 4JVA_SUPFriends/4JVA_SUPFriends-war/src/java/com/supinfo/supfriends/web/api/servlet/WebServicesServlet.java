/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.api.servlet;

import com.google.gson.Gson;
import com.supinfo.supfriends.ejb.entity.GroupEntity;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.GroupFacade;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import com.supinfo.supfriends.web.api.model.ApiResponseMessageModel;
import com.supinfo.supfriends.web.api.model.GroupModel;
import com.supinfo.supfriends.web.api.model.ListGroupModel;
import java.io.IOException;
import java.util.List;
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
    
    @EJB
    GroupFacade groupFacade;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String responseJson = "";
        
        switch(action){
            case "update":
                String latitude, longitude;
                latitude = req.getParameter("latitude");
                longitude = req.getParameter("longitude");
                
                responseJson = update(username, latitude, longitude);
                
                break;
            case "listGroups":
                responseJson = listGroups();
                
                break;
        }
        
        resp.getWriter().println(responseJson);
    }
    
    private String listGroups(){
        List<GroupEntity> groupEntities = groupFacade.findAll();
        
        ListGroupModel model = new ListGroupModel();
        
        for(GroupEntity entity : groupEntities){
            GroupModel groupModel = new GroupModel();
            
            groupModel.setId(entity.getId());
            groupModel.setName(entity.getName());
            
            model.getGroups().add(groupModel);
        }
        
        return new Gson().toJson(model);
    }
    
    private String update(String username, String latitude, String longitude){
        UserEntity user = userFacade.findByUsername(username);
                
        user.setLongitude(Double.valueOf(longitude));
        user.setLatitude(Double.valueOf(latitude));
                
        userFacade.edit(user);
                
        return new Gson().toJson(new ApiResponseMessageModel().setSuccess(true));
    }
    
}
