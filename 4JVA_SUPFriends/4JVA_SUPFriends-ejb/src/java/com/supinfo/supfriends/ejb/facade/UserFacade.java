/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.facade;

import com.supinfo.supfriends.ejb.entity.metamodel.UserEntity_;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Méthode d'accès à la BDD pour l'entité User
 */
@Stateless
public class UserFacade {
    @PersistenceContext(unitName = "4JVA_SUPFriends-ejbPU")
    private EntityManager em;

    /**
     * Insert un User dans la base de données
     * @param userEntity UserEntity à insérer
     */
    public void create(UserEntity userEntity) {
        em.persist(userEntity);
    }

    /**
     * Modifie un utilisateur dans la base de données
     * @param userEntity UserEntity à modifier
     */
    public void edit(UserEntity userEntity) {
        em.merge(userEntity);
    }

    /**
     * Supprimer un User de la base de données
     * @param userEntity UserEntity à supprimer
     */
    public void remove(UserEntity userEntity) {
        em.remove(em.merge(userEntity));
    }

    /**
     * Récupère un utilisateur par son ID
     * @param id ID de l'utilisateur
     * @return UserEntity
     */
    public UserEntity find(Long id) {
        return em.find(UserEntity.class, id);
    }
    
    /**
     * Récupère un utilisateur depuis son nom d'utilisateur
     * @param username Nom d'utilisateur
     * @return UserEntity
     * @throws NoResultException Aucun utilisateur n'a été toruvé pour ce nom d'utilisateur
     */
    public UserEntity findByUsername(String username) throws NoResultException{
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> user = criteriaQuery.from(UserEntity.class);
        
        criteriaQuery.where(criteriaBuilder.equal(user.get(UserEntity_.userName), username));
        
        try{
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
    }

    /**
     * Récupère la liste de tout les utilisateurs
     * @return Liste de UserEntity
     */
    public List<UserEntity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserEntity.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * Récupère un certains nombre de User
     * @param range Tableau à 2 valeurs indiquant en première position la position de départ et en deuxième la position limite
     * @return Liste de UserEntity
     */
    public List<UserEntity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * @return Nombre d'utilisateur dans la base de données
     */
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<UserEntity> rt = cq.from(UserEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}