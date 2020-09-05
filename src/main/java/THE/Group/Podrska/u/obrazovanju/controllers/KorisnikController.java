package THE.Group.Podrska.u.obrazovanju.controllers;

import THE.Group.Podrska.u.obrazovanju.convertors.izKorisnikUFormu;
import THE.Group.Podrska.u.obrazovanju.email.MailService;
import THE.Group.Podrska.u.obrazovanju.models.Korisnik;
import THE.Group.Podrska.u.obrazovanju.models.RegisterDto;
import THE.Group.Podrska.u.obrazovanju.models.TipKorisnika;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.repositories.KorisnikRepository;
import THE.Group.Podrska.u.obrazovanju.repositories.UstanovaRepository;
import THE.Group.Podrska.u.obrazovanju.services.KorisnikService;
import THE.Group.Podrska.u.obrazovanju.services.TipKorisnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private UstanovaRepository ustanovaRepository;
    @Autowired
    private TipKorisnikaService tipKorisnikaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private THE.Group.Podrska.u.obrazovanju.convertors.izKorisnikUFormu izKorisnikUFormu;

    @Autowired
    public void setKorisnikURegisterDto(izKorisnikUFormu izKorisnikUFormu) {
        this.izKorisnikUFormu = izKorisnikUFormu;
    }

    @Autowired
    public void setKorisnikService(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }


    @RequestMapping("/korisnik-lista")
    public String listaKorisnika(Model model){
        model.addAttribute("korisnici", korisnikService.listAll());
        return "korisnik-lista";
    }

    @RequestMapping("/korisnik-prikazi/{korisnikID}")
    public String prikaziKorisnike(@PathVariable String korisnikID, Model model){
        model.addAttribute("korisnik", korisnikService.getById(Long.valueOf(korisnikID)));
        return "korisnik-prikazi";
    }

    @RequestMapping("/korisnik-izmeni/{korisnikID}")
    public String izmeniKorisnika(@PathVariable Long korisnikID, Model model){
        Korisnik korisnik = korisnikService.getById(Long.valueOf(korisnikID));
        RegisterDto registerDto = izKorisnikUFormu.convert(korisnik);
        model.addAttribute("registerDto", registerDto);
        return "admin-izmeniUstanovu";
    }

    @RequestMapping("/korisnik-novi")
    public String noviKorisnik(Model model){
        model.addAttribute("registerDto", new RegisterDto());
        return "korisnik-registracija";
    }

    @RequestMapping(value = "/korisnik-sacuvaj", method = RequestMethod.POST)
    public String sacuvajIliIzmeniKorisnika(@Valid RegisterDto registerDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "korisnik-registracija";
        }
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(1);
        if(uloge.isPresent()){
            Optional<Korisnik> probaKorisnik = korisnikRepository.findKorisnikByUsername(registerDto.getUsername());
            Optional<Korisnik> probaKorisnik2 = korisnikRepository.findKorisnikByEmail(registerDto.getEmail());
            Optional<Ustanova> probaUstanova = ustanovaRepository.findUstanovaByUsername(registerDto.getUsername());
            if(!probaKorisnik.isPresent()){
                if(!probaKorisnik2.isPresent()) {
                    if(!probaUstanova.isPresent()) {
                        registerDto.setUloge(uloge.get());
                        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
                        korisnikService.saveReg(registerDto);
                        String from = "thegrouppodrska@gmail.com";
                        String recipient = registerDto.getEmail();
                        String subject = "Uspesna registracija!";
                        String message = "Hvala sto ste se registrovali na sajt The Group. Vas username je: "+registerDto.getUsername();
                        mailService.prepareAndSend(from, recipient, subject, message);
                    }
                    else {
                        return "/korisnik-registracija";
                    }
                }
                else {
                    return "/korisnik-registracija";
                }
            }
            else {
                return "/korisnik-registracija";
            }
        }
        return "redirect:/loginForma/";
    }

    @RequestMapping("/korisnik-obrisi/{korisnikID}")
    public String obrisi(@PathVariable String korisnikID){
        korisnikService.deleteById(Long.valueOf(korisnikID));
        return "redirect:/korisnik-lista";
    }


    @Autowired
    private MailService mailService;

    //FORGOR PASS
    @PostMapping("/salji-pass")
    public String saljiPassword(@RequestParam(value="email", required = false) String email){

        Optional<Korisnik> korisnikRetard = korisnikRepository.findKorisnikByEmail(email);
        Korisnik korisnik = korisnikRetard.get();
        String sifra = passwordEncoder.encode(korisnik.getPassword());

            String from = "thegrouppodrska@gmail.com";
            String recipient = email;
            String subject = "Zaboravljena sifra";
            String message = "Vasa sifra je: " + sifra;

            mailService.prepareAndSend(from, recipient, subject, message);
            System.out.println("from: " + from);
            System.out.println("recipient: " + recipient);
            System.out.println("subject: " + subject);
            System.out.println("message: " + message);
        return "loginForma";

    }
}

