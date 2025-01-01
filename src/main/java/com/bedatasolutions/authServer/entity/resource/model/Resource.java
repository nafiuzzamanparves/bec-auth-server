package com.bedatasolutions.authServer.entity.resource.model;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.entity.role.model.Role;
import com.bedatasolutions.authServer.entity.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_resource", schema = "dbo")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_desc", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "resources")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "resources")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "map_resource_permission",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();
}