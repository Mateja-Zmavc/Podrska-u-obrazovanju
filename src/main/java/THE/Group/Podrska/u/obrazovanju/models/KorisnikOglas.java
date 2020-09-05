package THE.Group.Podrska.u.obrazovanju.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "korisnikOglas")
public class KorisnikOglas {

    @EmbeddedId
    private KorisnikOglasID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("korisnikID")
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("oglasID")
    private Oglas oglas;

    public KorisnikOglas(){}

    public KorisnikOglas(Korisnik korisnik, Oglas oglas) {

        this.korisnik = korisnik;
        this.oglas = oglas;
        this.id = new KorisnikOglasID(korisnik.getKorisnikID(), oglas.getOglasID());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if(o == null || getClass() !=o.getClass())
            return  false;

        KorisnikOglas that = (KorisnikOglas) o;
        return Objects.equals(korisnik, that.korisnik) && Objects.equals(oglas, that.oglas);
    }

    @Override
    public int hashCode(){
        return Objects.hash(korisnik,oglas);
    }

    public KorisnikOglasID getId() {
        return id;
    }

    public void setId(KorisnikOglasID id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }
}
