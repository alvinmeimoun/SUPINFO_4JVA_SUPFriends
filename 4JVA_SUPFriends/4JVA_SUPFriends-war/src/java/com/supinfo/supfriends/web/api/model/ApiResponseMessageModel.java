/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.web.api.model;

/**
 *
 * @author alvin
 */
public class ApiResponseMessageModel {
    
    private String message;
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public ApiResponseMessageModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public ApiResponseMessageModel setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    
    
    
}
