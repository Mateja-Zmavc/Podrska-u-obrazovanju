package THE.Group.Podrska.u.obrazovanju.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="korisnici")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NaturalIdCache
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long korisnikID;

    @NotBlank(message = "Unesite ime")
    @Column(name="ime", length = 50)
    private String ime;

    @NotBlank(message = "Unesite prezime")
    @Column(name="prezime", length = 50)
    private String prezime;

    @NotBlank(message = "Unesite email")
    @Column(name="email", length = 50)
    private String email;

    @NotBlank(message = "Unesite username")
    @Column(name="username", length = 50)
    private String username;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name="ulogeID")
    private TipKorisnika uloge;

    @OneToMany(
            mappedBy = "korisnik",
            cascade = CascadeType.ALL
            /*orphanRemoval = true*/
    )
    private List<KorisnikOglas> oglasi = new ArrayList<>();

    @OneToMany(
            mappedBy = "korisnik",
            cascade = CascadeType.ALL
            /*orphanRemoval = true*/
    )
    private List<KorisnikMail> mailingListe = new ArrayList<>();

    public Korisnik() {
    }

    public Korisnik(Long korisnikID, @NotBlank(message = "Unesite ime") String ime, @NotBlank(message = "Unesite prezime") String prezime, @NotBlank(message = "Unesite email") String email, @NotBlank(message = "Unesite username") String username, @Size(min = 8, max = 16, message = "Password mora imati izmedju 8 i 16 karaktera") String password, TipKorisnika uloge) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.username = username;
        this.password = password;
        this.uloge = uloge;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if(o == null || getClass() !=o.getClass())
            return  false;

        Korisnik korisnik = (Korisnik) o;
        return Objects.equals(ime, korisnik.ime);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ime);
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipKorisnika getUloge() {
        return uloge;
    }

    public String getNazivUloge(){return uloge.getNazivUloge();}

    public void setUloge(TipKorisnika uloge) {
        this.uloge = uloge;
    }

    public List<KorisnikOglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(List<KorisnikOglas> oglasi) {
        this.oglasi = oglasi;
    }

    public List<KorisnikMail> getMailingListe() {
        return mailingListe;
    }

    public void setMailingListe(List<KorisnikMail> mailingListe) {
        this.mailingListe = mailingListe;
    }
}
