package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.dao.ResourceDao;
import com.bedatasolutions.authServer.dao.RoleDao;
import com.bedatasolutions.authServer.dao.compositeKey.MapRoleResourceKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_role_resource", schema = "dbo")
public class MapRoleResourceDao {

    @EmbeddedId
    private MapRoleResourceKey id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDao role;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id", nullable = false)
    private ResourceDao resource;

    @Column(nullable = false)
    private String access;
}