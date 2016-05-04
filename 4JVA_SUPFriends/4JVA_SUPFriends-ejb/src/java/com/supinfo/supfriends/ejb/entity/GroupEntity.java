/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "GROUP")
public class GroupEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private List<UserEntity> listMembers;
    private Long ownerId;
    
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @NotNull
    @Column(name = "NAME", nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the ListMembers
     */
    @ManyToMany(mappedBy="groups")
    public List<UserEntity> getListMembers() {
        return listMembers;
    }

    /**
     * @param ListMembers the ListMembers to set
     */
    public void setListMembers(List<UserEntity> ListMembers) {
        this.listMembers = ListMembers;
    }

    /**
     * @return the ownerId
     */
    @NotNull
    @Column(name = "OWNERID", nullable=false)
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId the ownerId to set
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
   
    
}
