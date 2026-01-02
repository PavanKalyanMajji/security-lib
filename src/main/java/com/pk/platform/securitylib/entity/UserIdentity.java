package com.pk.platform.securitylib.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "user-identity")
public class UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "user_name",unique = true,nullable = false)
    private String name;

    @Column(name = "user_password",nullable = false)
    private String password;

    @Column(name = "user_roles",nullable = false)
    private String roles;

    public UserIdentity(UUID id, String name, String password, String roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public UserIdentity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserIdentity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
