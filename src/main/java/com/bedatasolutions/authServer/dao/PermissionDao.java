package com.bedatasolutions.authServer.dao;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_permission", schema = "dbo")
public class PermissionDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_desc", nullable = false)
    public String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<ResourceDao> resources = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ResourceDao> getResources() {
        return resources;
    }

    public void setResources(Set<ResourceDao> resources) {
        this.resources = resources;
    }
}