package ma.enset.patientsmvc.security.service;

import ma.enset.patientsmvc.security.entities.AppRole;
import ma.enset.patientsmvc.security.entities.AppUser;

public interface SecurityService {
AppUser saveNewUser(String userName,String password,String rePssword);

AppRole saveNewRole(String roleName,String description);
void addRoleToUser(String userName,String roleName);
void removeRoleFromUser(String userName,String roleName);
AppUser loadUserByUserName(String userName);


}
