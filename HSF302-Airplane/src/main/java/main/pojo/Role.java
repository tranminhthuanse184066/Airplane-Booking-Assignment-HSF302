package main.pojo;

import jakarta.persistence.*;
import main.enumerators.RoleEnum;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "roleName", nullable = false, unique = true, length = 50)
    private RoleEnum roleName;
    
    // Constructors
    public Role() {
    }
    
    public Role(RoleEnum roleName) {
        this.roleName = roleName;
    }
    
    // Getters and Setters
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public RoleEnum getRoleName() {
        return roleName;
    }
    
    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }
}
