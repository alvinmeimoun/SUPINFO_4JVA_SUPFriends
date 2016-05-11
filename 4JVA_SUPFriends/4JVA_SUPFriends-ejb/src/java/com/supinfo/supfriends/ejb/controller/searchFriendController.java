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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIMessage;
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
public class searchFriendController {
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private GroupFacade groupFacade;
    

    private List<UserEntity> listFriends;

    private DataModel<UserEntity> listFriendsDataModel;
    private String searchText;
    private String errorMessage;
    
    private Long groupId;
    
    private GroupEntity group;
    
    private UIComponent mybutton;
    

    public searchFriendController()
    {
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        String gpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("groupId");
        groupId = Long.valueOf(gpId);
        group = groupFacade.find(groupId);
        errorMessage = null;
       
    }
    
    public String searchFriendByPhoneNumber()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        UserEntity findUser = userFacade.findByPhoneNumber(searchText);
        if(findUser == null)
        {
            FacesMessage message = new FacesMessage("L'utilisateur n'a pas été trouvé ou son numéro de téléphone est incorrect.");
            context.addMessage(mybutton.getClientId(context), message);
            return null;
            
        }
        for (UserEntity user : group.getListMembers())
        {
            if(user.getId().equals(findUser.getId()))
            {
                FacesMessage message = new FacesMessage("L'ami portant le numéro " + searchText + " a déjà été ajouté dans le groupe " + group.getName());
                context.addMessage(mybutton.getClientId(context), message);
                searchText = null;
                return null; 
            }
        }
        if(!findUser.getId().equals(ServerConfig.GetUserId()))
        {
            listFriends = new ArrayList<UserEntity>();
            listFriends.add(findUser);
            setListFriendsDataModel(new ListDataModel<UserEntity>(listFriends));
            searchText = null;
            return "searchFriend?groupId=" + group.getId() + "&faces-redirect=true";
        }
        return null;    
    }
    
    public String addFriendToGroup()
    {
       
       UserEntity friendToAdd = listFriendsDataModel.getRowData();
       List<UserEntity> list = group.getListMembers();
       
       if(list == null)
       {
           list = new ArrayList<UserEntity>();
       }
       if(list.contains(friendToAdd))
       {
           return null ;
       }
        list.add(friendToAdd);
        groupFacade.edit(group);
        listFriends = null;
        listFriendsDataModel = null;
        searchText = null;

        return "detailsGroup.xhtml?groupId=" + group.getId() + "&faces-redirect=true";
       
       
    }

    /**
     * @return the listFriends
     */
    public List<UserEntity> getListFriends() {
        return listFriends;
    }

    /**
     * @param listFriends the listFriends to set
     */
    public void setListFriends(List<UserEntity> listFriends) {
        this.listFriends = listFriends;
    }

    /**
     * @return the searchText
     */
    public String getSearchText() {
        return searchText;
    }

    /**
     * @param searchText the searchText to set
     */
    public void setSearchText(String searchText) {
        this.searchText = searchText;
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
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the groupId
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
     * @return the mybutton
     */
    public UIComponent getMybutton() {
        return mybutton;
    }

    /**
     * @param mybutton the mybutton to set
     */
    public void setMybutton(UIComponent mybutton) {
        this.mybutton = mybutton;
    }

}
