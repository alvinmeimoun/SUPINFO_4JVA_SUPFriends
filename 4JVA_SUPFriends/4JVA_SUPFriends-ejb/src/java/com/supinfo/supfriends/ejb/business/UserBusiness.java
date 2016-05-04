/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.business;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.NoResultException;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Logique correspondant à l'entité User
 */
@ManagedBean
@SessionScoped
public class UserBusiness {
    
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
    
    private UserEntity loggedUser;
    public UserBusiness(){
     this.userFacade = new UserFacade();   
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
        loggedUser = null;
                //userFacade..login(username, password);
        
        if(null == loggedUser) {
            return null;
        }
        
        username = null;
        password = null;
        
        return null;
       
    }
    public String register() {
        
       UserEntity user = new UserEntity();
       user.setUserName(username);
       user.setPassword(password);
       user.setFirstName(firstname);
       user.setLastName(lastname);
       user.setEmail(email);
       user.setPhoneNumber(phonenumber);
       user.setGroups(new ArrayList<GroupEntity>());
       
      
        
       
       
       Long id = userFacade.create(user);
       user.setId(id);
       GroupEntity group = new GroupEntity();
       group.setOwnerId(user.getId());
       group.setName("test");
       List<UserEntity> list = new ArrayList<UserEntity>();
       list.add(user);
       group.setListMembers(list);
       groupFacade.create(group);
       
       return null;
        
       
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

}
