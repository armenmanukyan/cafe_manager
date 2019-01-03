package com.myCafe.core.dto;

import com.myCafe.common.enums.UserRole;
import com.myCafe.dal.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class CafeUser {

    public CafeUser() {

    }

    private Integer id;

    private UserRole role;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private Set<CafeTable> assignedTables;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<CafeTable> getAssignedTables() {
        return assignedTables;
    }

    public void setAssignedTables(Set<CafeTable> tables) {
        this.assignedTables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeUser user = (CafeUser) o;
        return Objects.equals(id, user.id) &&
                role == user.role &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(assignedTables, user.assignedTables);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role, firstName, lastName, userName, password, assignedTables);
    }
}
