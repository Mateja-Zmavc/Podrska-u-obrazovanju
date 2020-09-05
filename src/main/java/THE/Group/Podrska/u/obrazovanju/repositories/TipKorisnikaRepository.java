package THE.Group.Podrska.u.obrazovanju.repositories;

import THE.Group.Podrska.u.obrazovanju.models.TipKorisnika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKorisnikaRepository extends JpaRepository<TipKorisnika, Integer> {
}
