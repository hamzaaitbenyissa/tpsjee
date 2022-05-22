package application;


import framework.annotations.Autowired;
import framework.annotations.Component;
import framework.annotations.Qualifier;
import application.services.CustomerService;


@Component
public class CustomerComponent {

    //Qualifier used for services or autowire by type in case of multiple implementations.

    @Autowired
    @Qualifier(value = "UserServiceImpl")
    private CustomerService userService;

    public void FullName() {
        String username = userService.getFullName();
        System.out.println("Full Name: ====> " + username);
    }
}
