package com.tianlong.common.enums;

public enum Scopes {
    REFRESH_TOKEN;
    public String authority() {
        return "ROLE_" + this.name();
    }
}