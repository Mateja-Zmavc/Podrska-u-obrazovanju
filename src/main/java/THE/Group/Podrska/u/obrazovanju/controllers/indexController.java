package THE.Group.Podrska.u.obrazovanju.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/kontakt")
    public String kontakt(){
        return "kontakt";
    }

    @GetMapping("/o-nama")
    public String oNama(){
        return "o-nama";
    }
}
