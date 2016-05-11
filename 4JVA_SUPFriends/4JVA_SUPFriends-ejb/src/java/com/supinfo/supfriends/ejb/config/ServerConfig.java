/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.supfriends.ejb.config;

import java.util.Map;
import javax.faces.context.FacesContext;
import javax.jms.Session;

/**
 * Configuration
 */
public class ServerConfig {
    /**
     * URL de base de l'application
     */
    public static final String BASE_URL = "http://localhost:8080/4JVA_SUPFriends-war";
    public static final String CONTEXT_URL = "/4JVA_SUPFriends-war";
    
    public static Map<String,Object> GetSessionContext()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }
    
    public static Long GetUserId()
    {
        return Long.valueOf(GetSessionContext().get("id").toString());
    }
            
}

