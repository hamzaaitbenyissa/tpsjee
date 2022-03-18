package ma.enset.jpausers.service;

import ma.enset.jpausers.entities.Role;
import ma.enset.jpausers.entities.User;

import java.util.List;

public interface UserService {

    User addNewUser(User user);

    Role addNewRole(Role role);

    User findUserByName(String name);

    Role findRoleByRoleName(String rolename);

    Void addRoleToUser(String username, String rolename);

    User authentificate(String username,String password);

    List<User> findAllUsers();



}
