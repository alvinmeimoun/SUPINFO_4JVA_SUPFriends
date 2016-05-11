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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Antonin
 */
@ManagedBean
public class detailsGroupController {
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private GroupFacade groupFacade;
    
    @NotEmpty
    private DataModel<UserEntity> listFriendsDataModel;
    @NotEmpty
    private List<UserEntity> listFriends;
    @NotEmpty
    private List<UserEntity> listFriendsWithOwner;
    @NotEmpty
    private GroupEntity group;
    @NotEmpty
    private Long currentUserId;
    
    @NotEmpty
    private UserEntity owner;
    
    public detailsGroupController()
    {
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        
        String gpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("groupId");
        Long groupId = Long.valueOf(gpId);
        currentUserId = ServerConfig.GetUserId();
        group = groupFacade.find(groupId);
        owner = userFacade.find(group.getOwnerId());
        listFriends = group.getListMembers();
        listFriendsWithOwner = new ArrayList<UserEntity>(listFriends);
        
        listFriendsDataModel = new ListDataModel<UserEntity>(listFriends);
        // On ajoute l'owner pour la map
        if(!listFriendsWithOwner.contains(owner))
            listFriendsWithOwner.add(owner);
        
    }

    public String removeUserFromGroup()
    {
        UserEntity memberToRemove = listFriendsDataModel.getRowData();
        
        group.getListMembers().remove(memberToRemove);
        
        groupFacade.edit(group);
        return "detailsGroup?groupId="+ group.getId() + "&faces-redirect=true";
        //new detailsGroupController();
    }
    
    /**
     * @return the group
     */
    public GroupEntity getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    /**
     * @return the listFriendsDataModel
     */
    public DataModel<UserEntity> getListFriendsDataModel() {
        
        return listFriendsDataModel;
    }

    /**
     * @param listFriendsDataModel the listFriendsDataModel to set
     */
    public void setListFriendsDataModel(DataModel<UserEntity> listFriendsDataModel) {
        this.listFriendsDataModel = listFriendsDataModel;
    }

    /**
     * @return the currentUserId
     */
    public Long getCurrentUserId() {
        return currentUserId;
    }

    /**
     * @param currentUserId the currentUserId to set
     */
    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    /**
     * @return the owner
     */
    public UserEntity getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    /**
     * @return the listFriendsWithOwner
     */
    public List<UserEntity> getListFriendsWithOwner() {
        return listFriendsWithOwner;
    }

    /**
     * @param listFriendsWithOwner the listFriendsWithOwner to set
     */
    public void setListFriendsWithOwner(List<UserEntity> listFriendsWithOwner) {
        this.listFriendsWithOwner = listFriendsWithOwner;
    }
    
    
}
