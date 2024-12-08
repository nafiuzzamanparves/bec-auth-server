package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.dao.ResourceDao;
import com.bedatasolutions.authServer.dao.UserDao;
import com.bedatasolutions.authServer.dao.compositeKey.MapUserResourceKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_user_resource", schema = "dbo")
public class MapUserResourceDao {

    @EmbeddedId
    private MapUserResourceKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private UserDao user;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "resource_id", nullable = false)
    private ResourceDao resource;

    @Column(nullable = false)
    private String access; // Example values: "READ", "WRITE", "ADMIN"
}