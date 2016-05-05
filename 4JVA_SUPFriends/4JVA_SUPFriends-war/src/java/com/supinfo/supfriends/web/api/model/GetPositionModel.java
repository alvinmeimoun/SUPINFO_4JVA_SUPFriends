/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.api.model;

import java.util.List;

/**
 *
 * @author alvin
 */
public class GetPositionModel {
    
    private GroupModel group;
    
    private List<UserModel> positions;

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public List<UserModel> getPositions() {
        return positions;
    }

    public void setPositions(List<UserModel> positions) {
        this.positions = positions;
    }
    
    
}
