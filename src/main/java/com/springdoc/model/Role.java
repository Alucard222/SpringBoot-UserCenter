package com.springdoc.model;

import javax.persistence.*;

/**
 * Created by alucard on 8/4/17.
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Authority role;

//    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private List<User> users;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Authority getRole() {
        return role;
    }
    public void setRole(Authority role) {
        this.role = role;
    }
//    public List<User> getUsers() {
//        return users;
//    }
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
