package com.bedatasolutions.authServer.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PersistentSetMixin {
    @JsonIgnore
    private boolean wasInitialized;
}