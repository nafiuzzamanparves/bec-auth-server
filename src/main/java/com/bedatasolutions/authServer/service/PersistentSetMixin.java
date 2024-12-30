package com.bedatasolutions.authServer.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PersistentSetMixin {
    @JsonIgnore
    private boolean wasInitialized;
}