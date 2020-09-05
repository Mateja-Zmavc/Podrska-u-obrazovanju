package THE.Group.Podrska.u.obrazovanju.repositories;

import THE.Group.Podrska.u.obrazovanju.models.MailingLista;
import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MailingListaRespository extends JpaRepository<MailingLista, Long> {
    List<MailingLista> findAllByUstanova(Ustanova ustanova);
    Optional<MailingLista> findAllByEmailAndUstanova(String email, Ustanova ustanova);
}
