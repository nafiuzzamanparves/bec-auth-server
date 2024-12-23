package com.bedatasolutions.authServer.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_users", schema = "dbo")
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 32)
    private String phone;

    private Integer age;

    @Column(length = 128)
    private String address;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean isAccountNonExpired;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean isAccountNonLocked;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean isCredentialsNonExpired;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean enabled;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean idRoleResourceAccess;

    @ManyToMany
    @JoinTable(
            name = "map_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleDao> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "map_user_resource",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<ResourceDao> resources = new HashSet<>();
}