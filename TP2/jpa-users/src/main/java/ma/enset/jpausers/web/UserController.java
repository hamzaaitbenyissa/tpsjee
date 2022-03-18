package ma.enset.jpausers.web;


import ma.enset.jpausers.entities.User;
import ma.enset.jpausers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService  userService;

    @GetMapping("/users")
    public List<User> getUsers (){
        return userService.findAllUsers();
    }


    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username){
        return userService.findUserByName(username);
    }

}
