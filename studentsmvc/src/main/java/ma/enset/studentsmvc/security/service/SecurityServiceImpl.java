package ma.enset.studentsmvc.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.studentsmvc.security.entities.AppRole;
import ma.enset.studentsmvc.security.entities.AppUser;
import ma.enset.studentsmvc.security.repositories.AppRoleRepository;
import ma.enset.studentsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String userName, String password, String rePssword) {

        if (!password.equals(rePssword)) throw new RuntimeException("Password not match");
        String hashedPWD = passwordEncoder.encode(password);

        AppUser appUser = new AppUser();

        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(userName);
        appUser.setPassword(hashedPWD);
        appUser.setActive(true);
        AppUser savedAppUser = appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole = appRoleRepository.findByRoleName(roleName);

        if (appRole != null) throw new RuntimeException("Role " + roleName + "Already exist");

        appRole = new AppRole();

        appRole.setRoleName(roleName);
        appRole.setDescription(description);

        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {

        AppUser appUser = appUserRepository.findByUsername(userName);

        if (appUser == null) throw new RuntimeException("User not found!");

        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        if (appRole == null) throw new RuntimeException("Role Not found!");

        appUser.getAppRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String userName, String roleName) {


    }

    @Override
    public AppUser loadUserByUserName(String userName) {

        return appUserRepository.findByUsername(userName);
    }
}
