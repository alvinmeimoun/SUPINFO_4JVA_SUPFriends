/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.facade;

import com.supinfo.supfriends.ejb.entity.GroupEntity;
import com.supinfo.supfriends.ejb.entity.GroupEntity_;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import java.util.Arrays;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Antonin
 */
@Stateless
@LocalBean
public class GroupFacade {
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public GroupFacade()
    {
          emf = Persistence.createEntityManagerFactory("supfriends-ejbPU");
          em = emf.createEntityManager();
    }
   
    public void create(GroupEntity groupEntity) {
        try{
        em.getTransaction().begin();
        em.persist(groupEntity);
        em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


    public void edit(GroupEntity groupEntity) {
        try{
            em.getTransaction().begin();
            em.merge(groupEntity);
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }


    public void remove(GroupEntity groupEntity) {
        em.remove(em.merge(groupEntity));
    }

    public GroupEntity find(Long id) {
        return em.find(GroupEntity.class, id);
    }
    
    /**
     * Récupère un utilisateur depuis son nom d'utilisateur
     * @param username Nom d'utilisateur
     * @return UserEntity
     * @throws NoResultException Aucun utilisateur n'a été toruvé pour ce nom d'utilisateur
     */
    /*public UserEntity findByUsername(String username) throws NoResultException{
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> user = criteriaQuery.from(UserEntity.class);
        
        criteriaQuery.where(criteriaBuilder.equal(user.get(UserEntity_.userName), username));
        
        try{
            return em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException nre){
            return null;
        }
    }*/

    /**
     * Récupère la liste de tout les utilisateurs
     * @return Liste de UserEntity
     */
    public List<GroupEntity> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(GroupEntity.class));
        return em.createQuery(cq).getResultList();
    }

    public List<GroupEntity> findByUserId(Long userId) throws NoResultException{
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<GroupEntity> criteriaQuery = criteriaBuilder.createQuery(GroupEntity.class);
        Root<GroupEntity> user = criteriaQuery.from(GroupEntity.class);
        
        criteriaQuery.where(criteriaBuilder.equal(user.get(GroupEntity_.ownerId), userId));
        
        try{
            return em.createQuery(criteriaQuery).getResultList();
        } catch (NoResultException nre){
            return null;
        }
    }
    /**
     * Récupère un certains nombre de User
     * @param range Tableau à 2 valeurs indiquant en première position la position de départ et en deuxième la position limite
     * @return Liste de UserEntity
     */
    public List<UserEntity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(GroupEntity.class));
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
        Root<GroupEntity> rt = cq.from(UserEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
