package com.bedatasolutions.authServer.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_resource", schema = "dbo")
public class ResourceDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_desc", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "resources")
    private Set<UserDao> users = new HashSet<>();

    @ManyToMany(mappedBy = "resources")
    private Set<RoleDao> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "map_resource_permission",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionDao> permissions = new HashSet<>();
}