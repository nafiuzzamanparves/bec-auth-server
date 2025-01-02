package com.bedatasolutions.authServer.entity.map;

import com.bedatasolutions.authServer.entity.compositeKey.MapResourcePermissionKey;
import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.entity.resource.model.Resource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_resource_permission", schema = "dbo")
public class MapResourcePermission {

    @EmbeddedId
    private MapResourcePermissionKey id;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    // The 'access' field is for storing the access or other related information.
    @Column(name = "access", nullable = false)
    private Boolean access;

}