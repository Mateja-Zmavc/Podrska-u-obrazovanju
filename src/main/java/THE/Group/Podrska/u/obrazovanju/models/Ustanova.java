package THE.Group.Podrska.u.obrazovanju.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ustanove")
public class Ustanova {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer ustanoveID;

    @NotBlank(message = "Unesite naziv ustanove")
    @Column(name="naziv", length = 50)
    private String naziv;

    @Email(message = "Unesite email ustanove")
    @Column(name="email", length = 50)
    private String email;

    @NotBlank(message = "Unesite username ustanove")
    @Column(name="username", length = 50)
    private String username;

    @Column(name="password")
    private String password;

    @ManyToOne
    @JoinColumn(name="ulogeID")
    private TipKorisnika uloge;

    @OneToMany(mappedBy="ustanova", cascade = CascadeType.REMOVE)
    private Set<MailingLista> lista;

    @OneToMany(mappedBy="ustanove", cascade = CascadeType.ALL)
    private Set<Oglas> oglasi;

    public Ustanova(Integer ustanoveID, String naziv, String email, String username, String password, TipKorisnika uloge) {
        this.ustanoveID = ustanoveID;
        this.naziv = naziv;
        this.email = email;
        this.username = username;
        this.password = password;
        this.uloge = uloge;
    }

    public Integer getUstanoveID() {
        return ustanoveID;
    }

    public void setUstanoveID(Integer ustanoveID) {
        this.ustanoveID = ustanoveID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public TipKorisnika getUloge() {
        return uloge;
    }
    public String getNazivUloge(){return uloge.getNazivUloge();}

    public void setUloge(TipKorisnika uloge) {
        this.uloge = uloge;
    }

    public Set<MailingLista> getLista() {
        return lista;
    }

    public void setLista(Set<MailingLista> lista) {
        this.lista = lista;
    }

    public Set<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(Set<Oglas> oglasi) {
        this.oglasi = oglasi;
    }

    public Ustanova() {
    }



}
