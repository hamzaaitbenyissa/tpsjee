package ma.enset.patientsmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("/403")
    public String accessDenied(){
        return "403";
    }
}
