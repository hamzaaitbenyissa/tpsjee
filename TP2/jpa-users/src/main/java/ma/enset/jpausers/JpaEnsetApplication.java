package ma.enset.jpausers;

import ma.enset.jpausers.entities.Role;
import ma.enset.jpausers.entities.User;
import ma.enset.jpausers.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEnsetApplication.class, args);
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {

            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addNewUser(u);

            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            Stream.of("STUDENT", "USER", "ADMIN").forEach(r -> {
                Role role1 = new Role();
                role1.setRolename(r);
                userService.addNewRole(role1);
            });

            userService.addRoleToUser("user1", "STUDENT");
            userService.addRoleToUser("user1", "USER");
            userService.addRoleToUser("admin", "ADMIN");
            userService.addRoleToUser("admin", "USER");

            try {

                User user = userService.authentificate("user1", "123456");

                System.out.println(user.getUserId());
                System.out.println(user.getUsername());

                user.getRoles().forEach(role ->
                        System.out.println("Role -->" + role));
            } catch (Exception err) {
                err.printStackTrace();
            }

        };
    }
}
