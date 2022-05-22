package ma.enset.studentsmvc.security.repositories;

import ma.enset.studentsmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser,String> {

    AppUser findByUsername(String username);


}
