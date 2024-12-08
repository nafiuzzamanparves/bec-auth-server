package com.bedatasolutions.authServer.dao.mapEntity;

import com.bedatasolutions.authServer.dao.RoleDao;
import com.bedatasolutions.authServer.dao.UserDao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "map_user_role", schema = "dbo")
public class MapUserRoleDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDao user; // Reference to the User entity

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleDao role; // Reference to the Role entity

    @Column
    private String access; // Example values: "ASSIGNED", "PENDING" (if applicable)
}