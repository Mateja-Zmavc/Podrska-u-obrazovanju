package THE.Group.Podrska.u.obrazovanju.repositories;

import THE.Group.Podrska.u.obrazovanju.models.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findKorisnikByUsername(String username);
    Optional<Korisnik> findKorisnikByEmail(String email);
}
