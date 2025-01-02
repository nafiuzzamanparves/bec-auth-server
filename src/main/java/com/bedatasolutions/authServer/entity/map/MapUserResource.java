package com.bedatasolutions.authServer.entity.map;

import com.bedatasolutions.authServer.entity.compositeKey.MapUserResourceKey;
import com.bedatasolutions.authServer.entity.resource.model.Resource;
import com.bedatasolutions.authServer.entity.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_user_resource", schema = "dbo")
public class MapUserResource {

    @EmbeddedId
    private MapUserResourceKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private String access; // Example values: "READ", "WRITE", "ADMIN"
}