package ma.enset.studentsmvc.security.service;

import ma.enset.studentsmvc.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = securityService.loadUserByUserName(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getAppRoles().forEach(appRole -> {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appRole.getRoleName());
                    authorities.add(authority);
                }

        );
        User user = new User(appUser.getUsername(), appUser.getPassword(), authorities);

        return user;
    }
}
