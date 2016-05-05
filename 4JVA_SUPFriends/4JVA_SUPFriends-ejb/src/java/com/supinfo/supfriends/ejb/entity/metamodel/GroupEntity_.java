/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.entity.metamodel;

import com.supinfo.supfriends.ejb.entity.GroupEntity;
import com.supinfo.supfriends.ejb.entity.UserEntity;
import java.util.List;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 *
 * @author Antonin
 */
@StaticMetamodel(GroupEntity.class)
public class GroupEntity_ {
 
    public static volatile ListAttribute<GroupEntity, UserEntity> listMembers;
    public static volatile SingularAttribute<GroupEntity, String> name;
    public static volatile SingularAttribute<GroupEntity, Long> id;
    public static volatile SingularAttribute<GroupEntity, Long> ownerId;
}
