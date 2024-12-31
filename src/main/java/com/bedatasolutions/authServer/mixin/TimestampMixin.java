package com.bedatasolutions.authServer.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class TimestampMixin {
    @JsonCreator
    public TimestampMixin(@JsonProperty("time") long time) {
    }
}