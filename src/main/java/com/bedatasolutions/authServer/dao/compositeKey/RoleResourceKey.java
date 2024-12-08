package com.bedatasolutions.authServer.dao.compositeKey;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class RoleResourceKey implements Serializable {

    @Serial
    private static final long serialVersionUID = 1462648591694756328L;

    @Column(name = "role_id")
    public String roleId;

    @Column(name = "resource_id")
    public String resourceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleResourceKey that = (RoleResourceKey) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, resourceId);
    }
}