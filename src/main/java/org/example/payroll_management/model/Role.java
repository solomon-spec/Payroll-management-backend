package org.example.payroll_management.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    EMPLOYEE;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
