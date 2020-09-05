package THE.Group.Podrska.u.obrazovanju.controllers;

import THE.Group.Podrska.u.obrazovanju.models.Korisnik;
import THE.Group.Podrska.u.obrazovanju.models.MailingLista;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.repositories.KorisnikRepository;
import THE.Group.Podrska.u.obrazovanju.repositories.MailingListaRespository;
import THE.Group.Podrska.u.obrazovanju.services.MailingListaService;
import THE.Group.Podrska.u.obrazovanju.services.UstanovaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class MailingListaController {


    @Autowired
    private MailingListaService mailingListaService;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private MailingListaRespository mailingListaRespository;
    @Autowired
    private UstanovaService ustanovaService;


    @RequestMapping(value = "/mailingLista-sacuvaj/{ustanoveID}")
    public String sacuvajOglas(@PathVariable String ustanoveID) {

        //korisnik
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Korisnik> logovanKorisnik= korisnikRepository.findKorisnikByUsername(usernameLoginovanog);
        String email = logovanKorisnik.get().getEmail();

        //ustanova
        Ustanova ustanova = ustanovaService.getById(Integer.valueOf(ustanoveID));
        Optional<MailingLista> proba = mailingListaRespository.findAllByEmailAndUstanova(email, ustanova);
        if(!proba.isPresent()) {
            MailingLista mailingLista = new MailingLista();
            mailingLista.setEmail(email);
            mailingLista.setUstanova(ustanova);
            mailingListaService.save(mailingLista);
            return "redirect:/oglasi-prikazi/";
        }
        else {
            return "redirect:/oglasi-prikazi/";
        }
    }

}
