package THE.Group.Podrska.u.obrazovanju.services;


import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.models.UstanovaDto;

import java.util.List;
import java.util.Optional;

public interface UstanovaService {

    List<Ustanova> getUstanova();
    Optional<Ustanova> findById(Integer ustanoveID);
    Ustanova saveOrUpdate(Ustanova ustanova);
    Ustanova update(Ustanova ustanova);
    void deleteById(Integer ustanoveID);
    Ustanova getById(Integer ustanoveID);
    Ustanova saveUst(UstanovaDto ustanovaDto);
    Object listAll();
}
