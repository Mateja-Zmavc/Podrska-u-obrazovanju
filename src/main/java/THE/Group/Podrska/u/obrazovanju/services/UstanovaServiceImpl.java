package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.models.UstanovaDto;
import THE.Group.Podrska.u.obrazovanju.repositories.UstanovaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UstanovaServiceImpl implements UstanovaService {

    @Autowired
    private THE.Group.Podrska.u.obrazovanju.convertors.izFormeuUstanovu izFormeuUstanovu;

    @Autowired
    private UstanovaRepository ustanovaRepository;

    @Override
    public List<Ustanova> getUstanova() {
        return this.ustanovaRepository.findAll();
    }

    @Override
    public Optional<Ustanova> findById(Integer ustanoveID) {
        return this.ustanovaRepository.findById(ustanoveID);
    }

    @Override
    public void deleteById(Integer ustanoveID) {
        this.ustanovaRepository.deleteById(ustanoveID);

    }
    @Override
    public Ustanova getById(Integer ustanoveID) {
        return ustanovaRepository.findById(ustanoveID).orElse(null);
    }

    @Override
    public Ustanova saveOrUpdate(Ustanova ustanova) {
        ustanovaRepository.save(ustanova);
        return ustanova;
    }

    @Override
    public Ustanova update(Ustanova ustanova) {
        return null;
    }

    @Override
    public Ustanova saveUst(UstanovaDto ustanovaDto) {
        Ustanova novaUstanova = saveOrUpdate(izFormeuUstanovu.convert(ustanovaDto));
        return novaUstanova;
    }

    @Override
    public Object listAll() {
        List<Ustanova> ustanove = new ArrayList<>();
        ustanovaRepository.findAll().forEach(ustanove::add);
        return ustanove;
    }
}
