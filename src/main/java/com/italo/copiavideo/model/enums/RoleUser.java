package com.italo.copiavideo.model.enums;

public enum RoleUser {
    ADMIN("ADMIN"), USER("USER");

    private String role;

    RoleUser (String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
