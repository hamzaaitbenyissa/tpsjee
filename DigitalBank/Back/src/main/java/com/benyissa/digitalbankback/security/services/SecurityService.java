package com.benyissa.digitalbankback.security.services;

import com.benyissa.digitalbankback.security.entities.AppRole;
import com.benyissa.digitalbankback.security.entities.AppUser;

public interface SecurityService {
AppUser saveNewUser(String userName, String password, String rePssword);
AppRole saveNewRole(String roleName, String description);
void addRoleToUser(String userName,String roleName);
AppUser loadUserByUserName(String userName);


}
