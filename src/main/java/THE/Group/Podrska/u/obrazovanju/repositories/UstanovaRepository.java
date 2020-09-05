package THE.Group.Podrska.u.obrazovanju.repositories;

import THE.Group.Podrska.u.obrazovanju.models.Oglas;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UstanovaRepository extends JpaRepository<Ustanova, Integer> {
    Optional<Ustanova> findUstanovaByUsername(String username);
    Ustanova findUstanovaByOglasi(Oglas oglas);
}
