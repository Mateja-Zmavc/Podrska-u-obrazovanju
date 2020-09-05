package THE.Group.Podrska.u.obrazovanju.email;

import THE.Group.Podrska.u.obrazovanju.models.MailingLista;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.repositories.MailingListaRespository;
import THE.Group.Podrska.u.obrazovanju.repositories.UstanovaRepository;
import THE.Group.Podrska.u.obrazovanju.services.MailingListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EmailController {
    @Autowired
    private MailService mailService;
    @Autowired
   private MailingListaService mailingListaService;
    @Autowired
    private MailingListaRespository mailingListaRespository;
    @Autowired
    private UstanovaRepository ustanovaRepository;

    @PostMapping("/posalji-mejl")
    public String reverseWord( Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovaneUstanove = authentication.getName();
        Optional<Ustanova> loginovanaUstanova = ustanovaRepository.findUstanovaByUsername(usernameLoginovaneUstanove);
        Ustanova ustanova = loginovanaUstanova.get();

        String recipient;
        for (MailingLista li:mailingListaRespository.findAllByUstanova(ustanova)
             ) {
            String from = "thegrouppodrska@gmail.com";
            recipient = li.getEmail();
            String subject = "Dobrodosli";
            String message = "Hvala sto ste sabskrajbovali na nas oglas!";
            mailService.prepareAndSend(from, recipient, subject, message);
            System.out.println("from: " + from);
            System.out.println("recipient: " + recipient);
            System.out.println("subject: " + subject);
            System.out.println("message: " + message);
        }
        return "ustanova-slanjeMejla";

    }
}
