package com.benyissa.digitalbankback.security.repositories;


import com.benyissa.digitalbankback.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser,String> {

    AppUser findByUsername(String username);


}
