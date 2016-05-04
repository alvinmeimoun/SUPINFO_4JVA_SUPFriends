/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.business;

import com.supinfo.supfriends.ejb.entity.UserEntity;
import com.supinfo.supfriends.ejb.facade.UserFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * Logique correspondant à l'entité User
 */
@Stateless
@LocalBean
public class UserBusiness {
    
    @EJB
    private UserFacade userFacade;
    
    public UserBusiness(){
        
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

}
