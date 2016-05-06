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
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Antonin
 */
@ManagedBean
@SessionScoped
public class listGroupController {
 
    @EJB
    private static UserFacade userFacade;
    @EJB
    private static GroupFacade groupFacade;
    
    @NotEmpty
    private List<GroupEntity> listGroups;
    
    @NotEmpty
    private int ownerId;
    
    public listGroupController()
    {
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
    }

    /**
     * @return the listGroups
     */
    public List<GroupEntity> getListGroups() {
        if(listGroups == null)
        {
            listGroups = groupFacade.findByUserId(ServerConfig.GetUserId());
        }
        return listGroups;
    }

    /**
     * @param listGroups the listGroups to set
     */
    public void setListGroups(List<GroupEntity> listGroups) {
        this.listGroups = listGroups;
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
    public void remove()
    {
        
        
    }
}
