package ma.enset.jpausers.service;

import lombok.AllArgsConstructor;
import ma.enset.jpausers.entities.Role;
import ma.enset.jpausers.entities.User;
import ma.enset.jpausers.repositories.RoleRepository;
import ma.enset.jpausers.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Role findRoleByRoleName(String rolename) {
        return roleRepository.findByRolename(rolename);
    }

    @Override
    public Void addRoleToUser(String username, String rolename) {
        User user = findUserByName(username);
        Role role = findRoleByRoleName(rolename);

        if (user.getRoles() != null && role.getUsers() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

        return null;
    }

    @Override
    public User authentificate(String username, String password) {

        User user =userRepository.findByUsername(username);

        if(user==null) throw  new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)){
            return  user;
        }
        throw  new RuntimeException("Bad credentials");
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
