package THE.Group.Podrska.u.obrazovanju.controllers;

import THE.Group.Podrska.u.obrazovanju.models.Oglas;
import THE.Group.Podrska.u.obrazovanju.models.OglasDto;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.repositories.OglasRespository;
import THE.Group.Podrska.u.obrazovanju.repositories.UstanovaRepository;
import THE.Group.Podrska.u.obrazovanju.services.OglasService;
import THE.Group.Podrska.u.obrazovanju.services.UstanovaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
public class OglasController {

    @Autowired
    private UstanovaService ustanovaService;
    @Autowired
    private UstanovaRepository ustanovaRepository;
    @Autowired
    private OglasRespository oglasRespository;
    @Autowired
    private OglasService oglasService;

    private THE.Group.Podrska.u.obrazovanju.convertors.izOglasaUFormu izOglasaUFormu;

    @Autowired
    public void setOglasDto(THE.Group.Podrska.u.obrazovanju.convertors.izOglasaUFormu izOglasaUFormu) {
        this.izOglasaUFormu = izOglasaUFormu;
    }

    @Autowired
    public void setOglasService(OglasService oglasService) {
        this.oglasService = oglasService;
    }

    //KORISNIK
    @RequestMapping("/oglas")
    public String OglasaKorisnik(Model model){
        model.addAttribute("oglasi", oglasService.listAll());
        return "index-Oglas";
    }
    //KORISNIK
    @RequestMapping("/index")
    public String indexKorisnik(Model model){
        model.addAttribute("oglasi", oglasService.listAll());
        return "index";
    }

    //KORISNIK
    @RequestMapping("/oglas-prikazi/{oglasID}")
    public String prikaziOglase(@PathVariable String oglasID, Model model){
        Oglas oglas = oglasService.getById(Long.valueOf(oglasID));
        Ustanova ustanova = ustanovaRepository.findUstanovaByOglasi(oglas);
        model.addAttribute("oglas", oglas);
        model.addAttribute("ustanova", ustanova);
        return "index-Oglas";
    }

    //KORISNIK
    @RequestMapping("/oglasi-prikazi")
    public String prikazilistuOglasa(
    @RequestParam(value="oblast", required = false) String oblast,
    @RequestParam(value="grad", required = false) String grad,
    @RequestParam(value="nivo", required = false) String nivo,
    Model model
    ){
        List<Oglas> oglasi = oglasRespository.findAllByOblastAndGradAndNivo(oblast, grad, nivo);
        if(!oglasi.isEmpty()){
            model.addAttribute("oglasi", oglasi);
        }
        else {
            model.addAttribute("oglasi", oglasRespository.findAll());
        }
        return "index-listaOglasa";
    }

    //ADMIN
    @RequestMapping("/admin-oglas-lista")
    public String listaOglasaAdmin(Model model){
        model.addAttribute("oglasi", oglasService.listAll());
        return "admin-listaOglasa";
    }

    //ADMIN
    @RequestMapping("/admin-oglas-izmeni/{oglasID}")
    public String izmeniOglasAdmin(@PathVariable String oglasID, Model model){
        Oglas oglas = oglasService.getById(Long.valueOf(oglasID));
        OglasDto oglasDto = izOglasaUFormu.convert(oglas);
        model.addAttribute("oglasDto", oglasDto);
        return "admin-izmeniOglas";
    }

    //ADMIN
    @RequestMapping(value = "/admin-oglas-sacuvajIzmene", method = RequestMethod.POST)
    public String sacuvajIzmeneOglasaAdmin(@Valid OglasDto oglasDto, BindingResult bindingResult) {
        Optional<Oglas> oglasNas = oglasRespository.findById(oglasDto.getOglasID());
        oglasDto.setSlika(oglasNas.get().getSlika());
        oglasService.saveOgl(oglasDto);
        return "redirect:/admin-oglas-lista/";
    }

    //ADMIN
    @RequestMapping("/admin-oglas-obrisi/{oglasID}")
    public String obrisiOglasAdmin(@PathVariable String oglasID){
        oglasService.deleteById(Long.valueOf(oglasID));
        return "redirect:/admin-oglas-lista";
    }

    //USTANOVA
    @RequestMapping("/ustanova-oglas-lista")
    public String listaOglasaUstanova(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Ustanova> loginovanaUstanova= ustanovaRepository.findUstanovaByUsername(usernameLoginovanog);
        Ustanova ustanova = loginovanaUstanova.get();
        List<Oglas> oglasi = oglasRespository.findAllByUstanove(ustanova);
        model.addAttribute("oglasi", oglasi);
        return "ustanova-listaOglasa";
    }

    //USTANOVA
    @RequestMapping("/ustanova-oglas-izmeni/{oglasID}")
    public String izmeniOglasUstanova(@PathVariable String oglasID, Model model){
        Oglas oglas = oglasService.getById(Long.valueOf(oglasID));
        OglasDto oglasDto = izOglasaUFormu.convert(oglas);
        model.addAttribute("oglasDto", oglasDto);
        return "ustanova-izmeniOglas";
    }

    //USTANOVA
    @RequestMapping("/ustanova-oglas-novi")
    public String noviOglas(Model model){
        model.addAttribute("oglasDto", new OglasDto());
        return "ustanova-dodajOglas";
    }

    //USTANOVA
    @RequestMapping(value = "/ustanova-oglas-sacuvaj", method = RequestMethod.POST)
    public String sacuvajOglas(@Valid OglasDto oglasDto, BindingResult bindingResult) throws IOException {

        String uploadDirectory = System.getProperty("user.dir") + "/src/main/uploads/static/slikeOglasa";
        MultipartFile slika = oglasDto.getSlikaMulti();
        Path imageNameAndPath = Paths.get(uploadDirectory, slika.getOriginalFilename());
        String putanja = "../uploads/static/slikeOglasa/" + slika.getOriginalFilename();
        Files.write(imageNameAndPath, slika.getBytes());
        oglasDto.setSlika(putanja);
        if(bindingResult.hasErrors()){
            return "ustanova-dodajOglas";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameLoginovanog = authentication.getName();
        Optional<Ustanova> loginovanaUstanova= ustanovaRepository.findUstanovaByUsername(usernameLoginovanog);
        Integer idLoginovaneUstanove = loginovanaUstanova.get().getUstanoveID();
        Optional<Ustanova> ustanova = ustanovaService.findById(idLoginovaneUstanove);
        if(ustanova.isPresent()){
            oglasDto.setUstanove(ustanova.get());
            Oglas noviOglas = oglasService.saveOgl(oglasDto);
            return "redirect:/ustanova-oglas-lista/";
        }
        return "redirect:/ustanova-oglas-lista/";
    }

    //USTANOVA
    @RequestMapping(value = "/ustanova-oglas-sacuvajIzmene", method = RequestMethod.POST)
    public String sacuvajIzmeneOglasaUstanova(@Valid OglasDto oglasDto, BindingResult bindingResult) {
        Optional<Oglas> oglasNas = oglasRespository.findById(oglasDto.getOglasID());
        oglasDto.setSlika(oglasNas.get().getSlika());
        oglasService.saveOgl(oglasDto);
        return "redirect:/ustanova-oglas-lista/";
    }

    //USTANOVA
    @RequestMapping("/ustanova-oglas-obrisi/{oglasID}")
    public String obrisiOglasUstanova(@PathVariable String oglasID){
        oglasService.deleteById(Long.valueOf(oglasID));
        return "redirect:/ustanova-oglas-lista";
    }
}
