package THE.Group.Podrska.u.obrazovanju.repositories;

import THE.Group.Podrska.u.obrazovanju.models.Oglas;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OglasRespository extends JpaRepository<Oglas, Long> {
    List<Oglas> findAllByUstanove(Ustanova ustanove);
    List<Oglas> findAllByOblastAndGradAndNivo(String par1, String par2, String par3);
}
