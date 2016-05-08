/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.controller;

import com.supinfo.supfriends.ejb.entity.GroupEntity;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.GroupFacade;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
@SessionScoped
public class detailsGroupController {
    
    @EJB
    private static UserFacade userFacade;
    @EJB
    private static GroupFacade groupFacade;
    
    @NotEmpty
    private DataModel<UserEntity> listFriendsDataModel;
    @NotEmpty
    private GroupEntity group;
    
    public detailsGroupController()
    {
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        
        String gpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("groupId");
        Long groupId = Long.valueOf(gpId);
        group = groupFacade.find(groupId);
        listFriendsDataModel = new ListDataModel<UserEntity>(group.getListMembers());
        
    }

    public String removeUserFromGroup()
    {
        UserEntity memberToRemove = listFriendsDataModel.getRowData();
        
        group.getListMembers().remove(memberToRemove);
        
        groupFacade.edit(group);
        
        return "detailsGroup.xhtml?faces-redirect=true";
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
    
    
}
