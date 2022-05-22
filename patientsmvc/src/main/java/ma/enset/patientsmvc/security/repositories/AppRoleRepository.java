package ma.enset.patientsmvc.security.repositories;

import ma.enset.patientsmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {

    AppRole findByRoleName(String rolename);


}
