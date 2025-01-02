package com.bedatasolutions.authServer.entity.map;

import com.bedatasolutions.authServer.entity.compositeKey.MapUserRoleKey;
import com.bedatasolutions.authServer.entity.role.model.Role;
import com.bedatasolutions.authServer.entity.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_user_role", schema = "dbo")
public class MapUserRole {

    @EmbeddedId
    private MapUserRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column
    private String access; // Example values: "ASSIGNED", "PENDING" (if applicable)
}