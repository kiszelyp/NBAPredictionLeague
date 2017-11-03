/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.journaldev.jsf.beans;

import com.journaldev.jsf.dao.ListDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author kiszelyi
 */
@SessionScoped
@Named
public class ViewUsers implements Serializable {
    
    public ViewUsers(){
    }
    
    private List<String> users = new ArrayList<String>();

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }
    
    @PostConstruct
    public void populateEmployeeList() {
        for (String name : ListDAO.getAllUsers()) {
            this.users.add(name);
        }
    }
    
    public String listUsers() {
        return "userList";
    }
    
}
