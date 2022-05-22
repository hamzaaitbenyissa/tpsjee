package ma.enset.studentsmvc.security.repositories;

import ma.enset.studentsmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {

    AppRole findByRoleName(String rolename);


}
