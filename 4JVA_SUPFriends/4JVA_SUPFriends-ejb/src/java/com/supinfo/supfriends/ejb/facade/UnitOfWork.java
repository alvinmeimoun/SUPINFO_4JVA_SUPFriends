/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Antonin
 * Pas utilisé mais une idée a prévoir si probleme de contexte
 */
public class UnitOfWork {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    
    public UnitOfWork()
    {
         emf = Persistence.createEntityManagerFactory("supfriends-ejbPU");
         em = emf.createEntityManager();
        
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the emf
     */
    public EntityManagerFactory getEmf() {
        return emf;
    }

    /**
     * @param emf the emf to set
     */
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
