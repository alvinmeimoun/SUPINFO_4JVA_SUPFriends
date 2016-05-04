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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
 
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "FIRSTNAME", nullable=false)
    private String firstName;
    @Column(name = "LASTNAME", nullable=false)
    private String lastName;
    @Column(name = "EMAIL", nullable=false)
    private String email;
    @Column(name = "USERNAME", nullable=false, unique = true)
    private String userName;
    @Column(name = "PASSWORD", nullable=false)
    private String password;
    @Column(name = "PHONENUMBER", nullable=false)
    private String phoneNumber;
    
    @ManyToMany(mappedBy="listMembers")
    private List<GroupEntity> groups;
    
    @Column(name = "LATITUDE", nullable=true)
    private Double latitude;
    @Column(name = "LONGITUDE", nullable=true)
    private Double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
     @NotNull
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    @NotNull
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return the phoneNumber
     */
    @NotNull
    @Size(max = 10)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the groups
     */
    public List<GroupEntity> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<GroupEntity> groups) {
        this.groups = groups;
    }
}