package THE.Group.Podrska.u.obrazovanju.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "korisnikMail")
public class KorisnikMail {

    @EmbeddedId
    private KorisnikMailID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("korisnikID")
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mailingListeID")
    private MailingLista mailingLista;

    public KorisnikMail() {
    }

    public KorisnikMail(Korisnik korisnik, MailingLista mailingLista) {

        this.korisnik = korisnik;
        this.mailingLista = mailingLista;
        this.id = new KorisnikMailID(korisnik.getKorisnikID(), mailingLista.getMailingListeID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        KorisnikMail that = (KorisnikMail) o;
        return Objects.equals(korisnik, that.korisnik) && Objects.equals(mailingLista, that.mailingLista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(korisnik, mailingLista);

    }
}