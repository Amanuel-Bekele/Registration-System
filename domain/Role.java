package com.waaproject.registrationsystem.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "role"))
public class Role implements Serializable {

    public Role() {
    }

    public Role(String roleName) {
        this.name = roleName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    @Override
    public boolean equals(Object obj) {
        Role theRole = (Role) obj;
        return this.name.equals(theRole.getName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}