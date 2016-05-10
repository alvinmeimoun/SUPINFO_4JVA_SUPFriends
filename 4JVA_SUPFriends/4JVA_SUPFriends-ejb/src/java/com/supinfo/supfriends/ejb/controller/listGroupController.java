/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.controller;

import com.supinfo.supfriends.ejb.config.ServerConfig;
import com.supinfo.supfriends.ejb.entity.GroupEntity;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.GroupFacade;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Antonin
 */
@ManagedBean
public class listGroupController {
 
    @EJB
    private static UserFacade userFacade;
    @EJB
    private static GroupFacade groupFacade;
    
    @NotEmpty
    private List<GroupEntity> listGroups;
    @NotEmpty
    private DataModel<GroupEntity> listGroupsDataModel;
    @NotEmpty
    private UserEntity currentUser;
    
    @NotEmpty
    private int ownerId;
    
    public listGroupController()
    {
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        
        initDatas();
    }
    
    public void initDatas()
    {

            Long userId = ServerConfig.GetUserId();
            listGroups = groupFacade.findByUserId(userId);
            currentUser = userFacade.find(userId);
            if(currentUser.getGroups().size() > 0)
            {
                listGroups.addAll(currentUser.getGroups());
            }
            setListGroupsDataModel(new ListDataModel<GroupEntity>(listGroups));
        
    }

    /**
     * @return the listGroups
     */
    public List<GroupEntity> getListGroups() {
        
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
    public String remove()
    {
       GroupEntity group =  getListGroupsDataModel().getRowData();
       boolean isDeleted = groupFacade.remove(group);
       if(isDeleted)
           return "listGroups?faces-redirect=true";
       return null;
    }

    /**
     * @return the currentUser
     */
    public UserEntity getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     */
    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return the listGroupsDataModel
     */
    public DataModel<GroupEntity> getListGroupsDataModel() {
        return listGroupsDataModel;
    }

    /**
     * @param listGroupsDataModel the listGroupsDataModel to set
     */
    public void setListGroupsDataModel(DataModel<GroupEntity> listGroupsDataModel) {
        this.listGroupsDataModel = listGroupsDataModel;
    }
}
