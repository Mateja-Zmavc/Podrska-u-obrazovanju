package THE.Group.Podrska.u.obrazovanju.models;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="oglasi")
@NaturalIdCache
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Oglas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long oglasID;

    @NotBlank(message = "Unesite naziv oglasa!")
    @Column(name="naziv", length = 50)
    private String naziv;

    @Column(name="oblast", length = 50)
    private String oblast;

    @Column(name="grad", length = 50)
    private String grad;

    @Column(name="nivo", length = 50)
    private String nivo;

    /*@Size(min = 20, max = 200, message = "Opis mora imati izmedju 20 i 200 karaktera")*/
    @Column(name="opis", length = 200)
    private String opis;

    @NotNull(message = "Unesite cenu oglasa")
    @Column(name="cena")
    private Float cena;

    @Column(name="slika", length = 255)
    private String slika;

    @ManyToOne
    @JoinColumn(name="ustanoveID")
    private Ustanova ustanove;

    @OneToMany(
            mappedBy = "oglas"
            /*orphanRemoval = true*/
    )
    private List<KorisnikOglas> korisnici = new ArrayList<>();

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if(o == null || getClass() !=o.getClass())
            return  false;

        Oglas oglas = (Oglas) o;
        return Objects.equals(naziv, oglas.naziv);
    }

    @Override
    public int hashCode(){
        return Objects.hash(naziv);
    }

    public Oglas() {
    }

    public Oglas(Long oglasID, String naziv, String oblast, String grad, String nivo, String opis, @NotNull(message = "Unesite cenu oglasa") Float cena, String slika, Ustanova ustanove, List<KorisnikOglas> korisnici) {
        this.oglasID = oglasID;
        this.naziv = naziv;
        this.oblast = oblast;
        this.grad = grad;
        this.nivo = nivo;
        this.opis = opis;
        this.cena = cena;
        this.slika = slika;
        this.ustanove = ustanove;
        this.korisnici = korisnici;
    }

    public Long getOglasID() {
        return oglasID;
    }

    public void setOglasID(Long oglasID) {
        this.oglasID = oglasID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }

    public Ustanova getUstanove() {
        return ustanove;
    }

    public void setUstanove(Ustanova ustanove) {
        this.ustanove = ustanove;
    }

    public List<KorisnikOglas> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<KorisnikOglas> korisnici) {
        this.korisnici = korisnici;
    }
}
