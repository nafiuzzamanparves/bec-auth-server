package com.bedatasolutions.authServer.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_permission", schema = "dbo")
public class PermissionDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_desc", nullable = false)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<ResourceDao> resources = new HashSet<>();
}