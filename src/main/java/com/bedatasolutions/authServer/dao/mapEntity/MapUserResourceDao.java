package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.dao.ResourceDao;
import com.bedatasolutions.authServer.dao.UserDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_user_resource", schema = "dbo")
public class MapUserResourceDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDao user; // Reference to the User entity

    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private ResourceDao resource; // Reference to the Resource entity

    @Column(nullable = false)
    private String access; // Example values: "READ", "WRITE", "ADMIN"
}