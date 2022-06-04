package com.benyissa.digitalbankback.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserForm{
    private String username;
    private String roleName;
}
