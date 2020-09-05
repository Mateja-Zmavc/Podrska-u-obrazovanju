package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.TipKorisnika;

import java.util.Optional;

public interface TipKorisnikaService {
    Optional<TipKorisnika> findById(Integer ulogeID);
}
