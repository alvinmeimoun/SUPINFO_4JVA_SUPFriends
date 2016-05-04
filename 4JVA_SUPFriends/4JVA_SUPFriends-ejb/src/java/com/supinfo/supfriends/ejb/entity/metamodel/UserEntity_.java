/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.entity.metamodel;

import com.supinfo.supfriends.ejb.entity.UserEntity;
import java.util.List;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserEntity.class)
public class UserEntity_ { 

    public static volatile SingularAttribute<UserEntity, String> lastName;
    public static volatile SingularAttribute<UserEntity, String> firstName;
    public static volatile SingularAttribute<UserEntity, String> password;
    public static volatile SingularAttribute<UserEntity, String> postalCode;
    public static volatile SingularAttribute<UserEntity, Long> id;
    public static volatile SingularAttribute<UserEntity, Double> latitude;
    public static volatile SingularAttribute<UserEntity, Double> longitude;
    public static volatile SingularAttribute<UserEntity, String> userName;
    public static volatile SingularAttribute<UserEntity, String> email;
    public static volatile SingularAttribute<UserEntity_, List<GroupEntity_>> groups;

}