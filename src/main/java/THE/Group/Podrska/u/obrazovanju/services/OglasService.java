package THE.Group.Podrska.u.obrazovanju.services;

import THE.Group.Podrska.u.obrazovanju.models.Oglas;
import THE.Group.Podrska.u.obrazovanju.models.OglasDto;
import java.util.List;
import java.util.Optional;

public interface OglasService {

    List<Oglas> getOglas();
    Optional<Oglas> findById(Long oglasID);
    Oglas saveOrUpdate(Oglas oglas);
    void deleteById(Long oglasID);
    Oglas getById(Long oglasID);
    Oglas saveOgl(OglasDto oglasDto);
    Object listAll();

}
