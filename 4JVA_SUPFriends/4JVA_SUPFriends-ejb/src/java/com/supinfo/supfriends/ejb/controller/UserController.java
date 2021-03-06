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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Logique correspondant à l'entité User
 */
@ManagedBean
@SessionScoped
public class UserController {
    
    @EJB
    private UserFacade userFacade;
    @EJB
    private GroupFacade groupFacade;
    
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
    
    private UICommand mybutton;
    public UserController(){
        if(userFacade == null) userFacade = new UserFacade();
        if(groupFacade == null) groupFacade = new GroupFacade();
    }
    
    /**
     * Récupèré la liste de tous les utilisateurs
     * @return Liste de UserEntity
     */
    public List<UserEntity> findAll(){
        return userFacade.findAll();
    }
    
    /**
     * Insert ou met à jour un User
     * @param userObject UserEntity à ajouter
     */
    public void addOrUpdateUser(UserEntity userObject){
        boolean isNew = false;
        if(userObject.getId() == null || userObject.getId() == 0){
            isNew = true;
        } else {
            if(userFacade.find(userObject.getId()) == null){
                isNew = true;
            }
        }
        if(isNew){
            userFacade.create(userObject);
        } else {
            userFacade.edit(userObject);
        }
    }
    
    /**
     * Récupère un User par son nom d'utilisateur
     * @param username Nom d'utilisateur
     * @return UserEntity
     * @throws NoResultException Aucun utilisateur éxistant pour ce nom d'utilisateur
     */
    public UserEntity findByUsername(String username) throws NoResultException{
        return userFacade.findByUsername(username);
    }
    
    /**
     * Récupère un User par son id
     * @param id ID de l'utilisateur
     * @return UserEntity
     */
    public UserEntity find(Long id) {
         
         return userFacade.find(id);
     }
    
    public String login() {
        
        String passwordCrypted = DigestUtils.sha256Hex(password);
        loggedUser = userFacade.login(username, passwordCrypted);
        
        if(null == loggedUser) {
            errorMessage = "Le nom d'utilisateur ou le mot de passe est incorrect.";
            return null;
        }
        
        username = null;
        password = null;
        errorMessage = null;    
        HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //HttpServletResponse res = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", loggedUser.getUserName());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", loggedUser.getId());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                "password", passwordCrypted);
        
        req.getSession().setAttribute("username", loggedUser.getUserName());
        req.getSession().setAttribute("id", loggedUser.getId());
        req.getSession().setAttribute("password", passwordCrypted);
        
            return "connected_home?faces-redirect=true";       
    }
    
    public String register() {
     
        FacesContext context = FacesContext.getCurrentInstance();
        if(userFacade.findByUsername(username) != null)
        {
            FacesMessage message = new FacesMessage("Username déjà existant.");
            context.addMessage(getMybutton().getClientId(context), message);
            return null;
        }
       UserEntity user = new UserEntity();
       user.setUserName(username);
       String passwordCrypted = DigestUtils.sha256Hex(password);
       user.setPassword(passwordCrypted);
       user.setFirstName(firstname);
       user.setLastName(lastname);
       user.setEmail(email);
       user.setPhoneNumber(getPhonenumber());
       user.setGroups(new ArrayList<GroupEntity>());
       user.setLatitude(Double.valueOf(latitude));
       user.setLongitude(Double.valueOf(longitude));
      
       
       Long id = userFacade.create(user);
       if(null == id) {
            FacesMessage message = new FacesMessage("Un problème est survenu lors de la sauvegarde.");
            context.addMessage(getMybutton().getClientId(context), message);
            return null;
       }
       else {
            HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", user.getUserName());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", user.getId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("password", passwordCrypted);
            
            req.getSession().setAttribute("username", user.getUserName());
            req.getSession().setAttribute("id", user.getId());
            req.getSession().setAttribute("password", passwordCrypted);
            
            return "connected_home?faces-redirect=true";      
       }
    }
  
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity getLoggedUser() {
        return loggedUser;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
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
        return lastname;
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
        return email;
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
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
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
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the mybutton
     */
    public UICommand getMybutton() {
        return mybutton;
    }

    /**
     * @param mybutton the mybutton to set
     */
    public void setMybutton(UICommand mybutton) {
        this.mybutton = mybutton;
    }

}
