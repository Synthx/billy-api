package org.yezebi.billy.spring.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {
    @JsonProperty("USER")
    USER(1),
    @JsonProperty("EDITOR")
    EDITOR(10),
    @JsonProperty("ADMIN")
    ADMIN(100);

    private final Integer level;

    UserRole(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }
}
