package THE.Group.Podrska.u.obrazovanju.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class loginLogoutController {

    @RequestMapping("/")
    public String redirect(){
        return "redirect:/korisnik-novi";
    }

    @GetMapping("/admin-pocetna")
    public String adminDeo(){
        return "admin-pocetna";
    }

    @GetMapping("/ustanova-pocetna")
    public String ustanovaDeo(){
        return "ustanova-pocetna";
    }

    @GetMapping("/index")
    public String korisnikDeo(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "loginForma";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/forgotPass")
    public String sifra(){
        return "forgotPass";
    }
}
