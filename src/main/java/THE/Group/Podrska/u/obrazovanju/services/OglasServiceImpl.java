package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.Oglas;
import THE.Group.Podrska.u.obrazovanju.models.OglasDto;
import THE.Group.Podrska.u.obrazovanju.repositories.OglasRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OglasServiceImpl implements OglasService {

    @Autowired
    private THE.Group.Podrska.u.obrazovanju.convertors.izFormeUOglas izFormeUOglas;

    @Autowired
    private OglasRespository oglasRespository;

    @Override
    public List<Oglas> getOglas() {
        return this.oglasRespository.findAll();
    }

    @Override
    public Optional<Oglas> findById(Long oglasID) {
        return this.oglasRespository.findById(oglasID);
    }

    @Override
    public void deleteById(Long oglasID) {
        this.oglasRespository.deleteById(oglasID);

    }
    @Override
    public Oglas getById(Long oglasID) {
        return oglasRespository.findById(oglasID).orElse(null);
    }

    @Override
    public Oglas saveOrUpdate(Oglas oglas) {
        oglasRespository.save(oglas);
        return oglas;
    }

    @Override
    public Oglas saveOgl(OglasDto oglasDto) {
        Oglas noviOglas = saveOrUpdate(izFormeUOglas.convert(oglasDto));
        return noviOglas;
    }

    @Override
    public Object listAll() {
        List<Oglas> oglasi = new ArrayList<>();
        oglasRespository.findAll().forEach(oglasi::add);
        return oglasi;
    }

}
