package com.tianlong.gateway.security.model;

public enum Scopes {
    REFRESH_TOKEN;
    public String authority() {
        return "ROLE_" + this.name();
    }
}