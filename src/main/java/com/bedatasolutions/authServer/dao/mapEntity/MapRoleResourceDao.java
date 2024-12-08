package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.dao.ResourceDao;
import com.bedatasolutions.authServer.dao.RoleDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_role_resource", schema = "dbo")
public class MapRoleResourceDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDao role; // Reference to the Role entity

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private ResourceDao resource; // Reference to the Resource entity

    @Column(nullable = false)
    private String access; // Example values: "READ", "WRITE", "FULL_ACCESS"
}