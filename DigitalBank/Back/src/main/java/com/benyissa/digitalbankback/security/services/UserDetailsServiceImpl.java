package com.benyissa.digitalbankback.security.services;

import com.benyissa.digitalbankback.security.entities.AppUser;
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

        System.out.println("username : " + username);
        AppUser appUser = securityService.loadUserByUserName(username);

        System.out.println("appuser : " + username);

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
