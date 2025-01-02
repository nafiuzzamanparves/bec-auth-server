package com.bedatasolutions.authServer.entity.map;

import com.bedatasolutions.authServer.entity.compositeKey.MapRoleResourceKey;
import com.bedatasolutions.authServer.entity.resource.model.Resource;
import com.bedatasolutions.authServer.entity.role.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_role_resource", schema = "dbo")
public class MapRoleResource {

    @EmbeddedId
    private MapRoleResourceKey id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private String access;
}