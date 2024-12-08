package com.bedatasolutions.authServer.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_role", schema = "dbo")
public class RoleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_desc", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<UserDao> users = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "map_role_resource",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<ResourceDao> resources = new HashSet<>();
}