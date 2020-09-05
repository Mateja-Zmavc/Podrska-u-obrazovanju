package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.Korisnik;
import THE.Group.Podrska.u.obrazovanju.models.RegisterDto;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {

        List<Korisnik> getKorisnik();
        Korisnik getById(Long korisnikID);
        void deleteById(Long korisnikID);
        Korisnik saveOrUpdate(Korisnik korisnik);
        Korisnik saveReg(RegisterDto registerDto);
        Optional<Korisnik> findById(Long korisnikID);
        List<Korisnik> listAll();

}
