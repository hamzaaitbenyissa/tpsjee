package ma.enset.jpausers.repositories;

import ma.enset.jpausers.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRolename(String rolename);
}
