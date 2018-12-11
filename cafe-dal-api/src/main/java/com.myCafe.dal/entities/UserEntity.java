package com.myCafe.dal.entities;

import com.myCafe.common.enums.UserRole;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "`User`")
public class UserEntity {

    public UserEntity(Integer id){
        this.id = id;
    }

    public UserEntity(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`UserName`", nullable = false, unique = true)
    private String userName;

    @Column(name = "`Password`", nullable = false)
    private String password;

    @Column(name = "`FirstName`", nullable = false)
    private String firstName;

    @Column(name = "`LastName`", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "`Role`", nullable = false, updatable = false)
    private UserRole role;

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(name = "`WaiterTables`", joinColumns = {@JoinColumn(name = "`WaiterId`")}, inverseJoinColumns = {@JoinColumn(name = "`TableId`")}
    )
    private Set<TableEntity> assignedTables;

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole userRole) {
        this.role = userRole;
    }

    public Set<TableEntity> getAssignedTables() {
        return assignedTables;
    }

    public void setAssignedTables(Set<TableEntity> assignedTables) {
        this.assignedTables = assignedTables;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                role == that.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, password, firstName, lastName, role);
    }
}
