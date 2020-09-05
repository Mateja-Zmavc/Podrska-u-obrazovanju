package THE.Group.Podrska.u.obrazovanju.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="mailingListe")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MailingLista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long mailingListeID;

    @Column(name="email")
    private String email;

    @ManyToOne
    @JoinColumn(name="ustanoveID")
    private Ustanova ustanova;

    @OneToMany(
            mappedBy = "mailingLista",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<KorisnikMail> korisnici = new ArrayList<>();


    public Long getMailingListeID() {
        return mailingListeID;
    }

    public void setMailingListeID(Long mailingListeID) {
        this.mailingListeID = mailingListeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ustanova getUstanova() {
        return ustanova;
    }

    public void setUstanova(Ustanova ustanova) {
        this.ustanova = ustanova;
    }

    public List<KorisnikMail> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<KorisnikMail> korisnici) {
        this.korisnici = korisnici;
    }
}

