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
@NoArgsConstructor
@AllArgsConstructor
public class UserResourceKey implements Serializable {

    @Serial
    private static final long serialVersionUID = -7241309041997097678L;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "resource_id")
    public String resourceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResourceKey that = (UserResourceKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, resourceId);
    }
}