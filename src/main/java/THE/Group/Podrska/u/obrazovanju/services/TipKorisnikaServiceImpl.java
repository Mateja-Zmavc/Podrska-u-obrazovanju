package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.TipKorisnika;
import THE.Group.Podrska.u.obrazovanju.repositories.TipKorisnikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TipKorisnikaServiceImpl implements TipKorisnikaService {

    @Autowired
    private TipKorisnikaRepository tipKorisnikaRepository;

    @Override
    public Optional<TipKorisnika> findById(Integer ulogeID) {
        return this.tipKorisnikaRepository.findById(ulogeID);
    }
}
