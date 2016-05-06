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
import javax.faces.bean.SessionScoped;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Antonin
 */
@ManagedBean
@SessionScoped
public class editProfileController {
    
    @EJB
    private static UserFacade userFacade;
    @EJB
    private static GroupFacade groupFacade;
    
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phonenumber;
    
    @NotEmpty
    private String latitude;
    @NotEmpty
    private String longitude;
        
    private String errorMessage;
    
    private UserEntity loggedUser;
    
    public editProfileController(){
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
        
        Long idUser = ServerConfig.GetUserId();
        setLoggedUser(userFacade.find(idUser));
    }
    
    
    public String saveChanges()
    {

        
        getLoggedUser().setFirstName(firstname);
        getLoggedUser().setLastName(lastname);
        getLoggedUser().setEmail(email);
        getLoggedUser().setLatitude(Double.valueOf(latitude));
        getLoggedUser().setLongitude(Double.valueOf(longitude));
        getLoggedUser().setPassword(password);
        getLoggedUser().setPhoneNumber(phonenumber);
        getLoggedUser().setUserName(username);
        
        boolean isEdited = userFacade.edit(getLoggedUser());
        
        if(!isEdited)
        {
            return null;
        }
        return "connected_home?faces-redirect=true";
        
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return loggedUser.getUserName();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return loggedUser.getPassword();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return loggedUser.getFirstName();
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return loggedUser.getLastName();
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return loggedUser.getEmail();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return loggedUser.getPhoneNumber();
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return loggedUser.getLatitude().toString();
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return loggedUser.getLongitude().toString();
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
     * @return the loggedUser
     */
    public UserEntity getLoggedUser() {
        return loggedUser;
    }

    /**
     * @param loggedUser the loggedUser to set
     */
    public void setLoggedUser(UserEntity loggedUser) {
        this.loggedUser = loggedUser;
    }
}