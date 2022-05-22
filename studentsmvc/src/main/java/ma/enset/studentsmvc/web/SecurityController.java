package ma.enset.studentsmvc.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SecurityController implements ErrorController {


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "handleErrors/404";
            }

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "handleErrors/403";
            }
        }
        return "handleErrors/unknownerror";
    }

    @GetMapping("/403")
    public String accessDeniedPage(){
        return "handleErrors/403";
    }

    @GetMapping("/login")
    public String login(Model model, String error,String logout) {

        if (error != null)
            model.addAttribute("ErrorMessage", "username or password incorrect !");
        else
            model.addAttribute("ErrorMessage", "");
        if (logout != null)
            return "redirect:/index";


        return "login";
    }

}
