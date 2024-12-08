package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.dao.PermissionDao;
import com.bedatasolutions.authServer.dao.ResourceDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_resource_permission", schema = "dbo")
public class MapResourcePermissionDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-one relationship with ResourceDao
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private ResourceDao resource;

    // Many-to-one relationship with PermissionDao
    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionDao permission;

    // The 'access' field is for storing the access or other related information.
    @Column(name = "access", nullable = false)
    private Boolean access;

}