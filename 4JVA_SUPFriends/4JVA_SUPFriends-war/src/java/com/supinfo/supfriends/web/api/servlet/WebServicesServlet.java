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
import com.supinfo.supfriends.web.api.model.GetPositionModel;
import com.supinfo.supfriends.web.api.model.GroupModel;
import com.supinfo.supfriends.web.api.model.ListGroupModel;
import com.supinfo.supfriends.web.api.model.UserModel;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

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
        String encodedPassword = DigestUtils.sha256Hex(password);
        String responseJson = "";
        
        UserEntity user = userFacade.findByUsername(username);
        if(user == null){
            resp.setStatus(403);
            resp.getWriter().println(String.format("User %s doesn't exists", username));
            return;
        }
        if(!user.getPassword().equals(encodedPassword)){
            resp.setStatus(401);
            resp.getWriter().println("Wrong password");
            return;
        }
        
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
            case "getPosition":
                String groupId = req.getParameter("groupID");
                responseJson = getPosition(groupId);
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
    
    private String getPosition(String groupId){
        GroupEntity group = groupFacade.find(Long.valueOf(groupId));
        List<UserEntity> users = group.getListMembers();
        
        GetPositionModel model = new GetPositionModel();
        
        model.getGroup().setId(group.getId());
        model.getGroup().setName(group.getName());
        
        for(UserEntity user : users){
            UserModel userModel = new UserModel();
            
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setLatitude(user.getLatitude());
            userModel.setLongitude(user.getLongitude());
            userModel.setUserId(user.getId());
            
            model.getPositions().add(userModel);
        }
        
        return new Gson().toJson(model);
    }
    
}
