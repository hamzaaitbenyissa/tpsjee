package ma.enset.jpausers.repositories;

import ma.enset.jpausers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {

    User findByUsername(String userName);
    List<User> findAll();

}
