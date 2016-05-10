/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.controller;

import com.supinfo.supfriends.ejb.config.ServerConfig;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.GroupFacade;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Antonin
 */
@ManagedBean
@RequestScoped
public class indexController {
    
    @EJB
    private static UserFacade userFacade;
    @EJB
    private static GroupFacade groupFacade;
    
    private int totalNbGroups;
    private int totalNbUsers;
    private int totalNbCoordinates;
    
    public indexController(){
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        
       totalNbGroups = groupFacade.count();
       totalNbUsers = userFacade.count();
       totalNbCoordinates = totalNbUsers * 2;

    }

    /**
     * @return the totalNbGroups
     */
    public int getTotalNbGroups() {
        return totalNbGroups;
    }

    /**
     * @param totalNbGroups the totalNbGroups to set
     */
    public void setTotalNbGroups(int totalNbGroups) {
        this.totalNbGroups = totalNbGroups;
    }

    /**
     * @return the totalNbUsers
     */
    public int getTotalNbUsers() {
        return totalNbUsers;
    }

    /**
     * @param totalNbUsers the totalNbUsers to set
     */
    public void setTotalNbUsers(int totalNbUsers) {
        this.totalNbUsers = totalNbUsers;
    }

    /**
     * @return the totalNbCoordinates
     */
    public int getTotalNbCoordinates() {
        return totalNbCoordinates;
    }

    /**
     * @param totalNbCoordinates the totalNbCoordinates to set
     */
    public void setTotalNbCoordinates(int totalNbCoordinates) {
        this.totalNbCoordinates = totalNbCoordinates;
    }
    
}
