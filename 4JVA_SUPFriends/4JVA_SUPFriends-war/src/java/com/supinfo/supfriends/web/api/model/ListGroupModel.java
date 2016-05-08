/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alvin
 */
public class ListGroupModel {
    
    private List<GroupModel> groups;
    
    public ListGroupModel(){
        groups = new ArrayList<>();
    }
    
    public List<GroupModel> getGroups(){
        return groups;
    }
    
    public void setGroups(List<GroupModel> groups){
        this.groups = groups;
    }
}
