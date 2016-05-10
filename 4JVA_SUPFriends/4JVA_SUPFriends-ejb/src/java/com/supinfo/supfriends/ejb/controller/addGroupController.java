/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.controller;

import com.supinfo.supfriends.ejb.config.ServerConfig;
import com.supinfo.supfriends.ejb.entity.GroupEntity;
import com.supinfo.supfriends.ejb.facade.GroupFacade;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Antonin
 */
@ManagedBean
@SessionScoped
public class addGroupController {
    @EJB
    private static UserFacade userFacade;
    @EJB
    private static GroupFacade groupFacade;
    
    @NotEmpty
    private String groupName;
    
    @NotEmpty
    private int ownerId;

    public addGroupController()
    {
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        
    }

    public String addGroup() throws IOException
    {
        GroupEntity group = new GroupEntity();
        group.setName(groupName);
        Long id = Long.valueOf( FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id").toString());
        group.setOwnerId(id);
        
        groupFacade.create(group);
        
        if(group.getId() == null)
        {
            return null;
        }
        groupName = null;
        
        return "listGroups?faces-redirect=true";
    }
    
    
    
    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the ownerId
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
