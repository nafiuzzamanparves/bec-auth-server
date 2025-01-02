package com.bedatasolutions.authServer.entity.permission.model;

import com.bedatasolutions.authServer.entity.resource.model.Resource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_permission", schema = "dbo")
public class Permission {

    @Column(name = "tx_desc", nullable = false)
    public String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "permissions")
    private Set<Resource> resources = new HashSet<>();
}