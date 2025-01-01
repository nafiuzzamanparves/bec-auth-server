package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.entity.permission.model.Permission;
import com.bedatasolutions.authServer.entity.resource.model.Resource;
import com.bedatasolutions.authServer.dao.compositeKey.MapResourcePermissionKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_resource_permission", schema = "dbo")
public class MapResourcePermissionDao {

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