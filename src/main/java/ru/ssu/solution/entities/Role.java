package ru.ssu.solution.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(new HashSet<Permission>(){{
        add(Permission.FILES_READ);
        add(Permission.USERS_READ);
    }}),
    USER(new HashSet<Permission>(){{
        add(Permission.FILES_READ);
    }});

    private final Set<Permission> permissions;

    Role(HashSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
