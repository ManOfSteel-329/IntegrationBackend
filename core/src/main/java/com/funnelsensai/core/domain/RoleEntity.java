package com.funnelsensai.core.domain;

public class RoleEntity {

    public static final RoleEntity ADMIN = new RoleEntity("ADMIN");
    public static final RoleEntity USER = new RoleEntity("USER");

    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }
}
