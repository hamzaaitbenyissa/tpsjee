package com.benyissa.digitalbankback.security.repositories;

import com.benyissa.digitalbankback.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {

    AppRole findByRoleName(String rolename);


}
