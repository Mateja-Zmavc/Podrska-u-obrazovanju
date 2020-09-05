package THE.Group.Podrska.u.obrazovanju.controllers;

import THE.Group.Podrska.u.obrazovanju.models.TipKorisnika;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.models.UstanovaDto;
import THE.Group.Podrska.u.obrazovanju.repositories.MailingListaRespository;
import THE.Group.Podrska.u.obrazovanju.repositories.UstanovaRepository;
import THE.Group.Podrska.u.obrazovanju.services.MailingListaService;
import THE.Group.Podrska.u.obrazovanju.services.TipKorisnikaService;
import THE.Group.Podrska.u.obrazovanju.services.UstanovaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class UstanoveController {

    @Autowired
    private UstanovaService ustanovaService;
    @Autowired
    private UstanovaRepository ustanovaRepository;
    @Autowired
    private TipKorisnikaService tipKorisnikaService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailingListaService mailingListaService;
    @Autowired
    private MailingListaRespository mailingListaRespository;

    private THE.Group.Podrska.u.obrazovanju.convertors.izUstanoveUFormu izUstanoveUFormu;

    @Autowired
    public void setUstanovaDto(THE.Group.Podrska.u.obrazovanju.convertors.izUstanoveUFormu izUstanoveUFormu) {
        this.izUstanoveUFormu = izUstanoveUFormu;
    }

    @Autowired
    public void setUstanovaService(UstanovaService ustanovaService) {
        this.ustanovaService = ustanovaService;
    }

    @RequestMapping({"/ustanove-lista", "/ustanove"})
    public String listaUstanova(Model model){
        model.addAttribute("ustanove", ustanovaService.listAll());
        return "admin-listaUstanova";
    }

    @RequestMapping("/ustanove-prikazi/{ustanoveID}")
    //bilo je string i za oglas kontroler
    public String prikaziUstanove(@PathVariable String ustanoveID, Model model){
        model.addAttribute("ustanova", ustanovaService.getById(Integer.valueOf(ustanoveID)));
        return "ustanove/prikazi";
    }

    @RequestMapping("/ustanove-izmeni/{ustanoveID}")
    public String izmeniUstanovu(@PathVariable String ustanoveID, Model model){
        Ustanova ustanova = ustanovaService.getById(Integer.valueOf(ustanoveID));
        UstanovaDto ustanovaDto = izUstanoveUFormu.convert(ustanova);

        model.addAttribute("ustanovaDto", ustanovaDto);
        return "admin-izmeniUstanovu";
    }

    @RequestMapping("/ustanove-novi")
    public String novaUstanova(Model model){
        model.addAttribute("ustanovaDto", new UstanovaDto());
        return "admin-dodajUstanovu";
    }

    @RequestMapping(value = "/ustanove-sacuvaj", method = RequestMethod.POST)
    public String sacuvajIliIzmeniUstanovu(@Valid UstanovaDto ustanovaDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin-dodajUstanovu";
        }
        Optional<Ustanova> probaUstanova = ustanovaRepository.findUstanovaByUsername(ustanovaDto.getUsername());
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(3);
        if(uloge.isPresent()){
            if(!probaUstanova.isPresent()) {
                ustanovaDto.setUloge(uloge.get());
                ustanovaDto.setPassword(passwordEncoder.encode(ustanovaDto.getPassword()));
                ustanovaService.saveUst(ustanovaDto);
            }
            else {
                return "admin-dodajUstanovu";
            }

        }
        return "redirect:/ustanove-lista";
    }

    @RequestMapping(value = "/ustanove-izmena", method = RequestMethod.POST)
    public String izmeniUstanovu(@Valid UstanovaDto ustanovaDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "admin-dodajUstanovu";
        }
        Optional<TipKorisnika> uloge = tipKorisnikaService.findById(3);
        if(uloge.isPresent()){
                ustanovaDto.setUloge(uloge.get());
                ustanovaService.saveUst(ustanovaDto);
        }
        return "redirect:/ustanove-lista";
    }

    @RequestMapping("/ustanove-obrisi/{ustanoveID}")
    public String obrisi(@PathVariable String ustanoveID){
        ustanovaService.deleteById(Integer.valueOf(ustanoveID));
        return "redirect:/ustanove-lista";
    }

    @RequestMapping("/slanje-mejla")
    public String listaMejlova(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovaneUstanove = authentication.getName();
        Optional<Ustanova> loginovanaUstanova = ustanovaRepository.findUstanovaByUsername(usernameLoginovaneUstanove);
        Ustanova ustanova = loginovanaUstanova.get();
        model.addAttribute("mailingLista", mailingListaRespository.findAllByUstanova(ustanova));
        return "ustanova-slanjeMejla";
    }





}


