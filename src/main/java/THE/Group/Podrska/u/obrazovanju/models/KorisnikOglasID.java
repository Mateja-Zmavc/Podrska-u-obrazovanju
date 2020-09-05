package THE.Group.Podrska.u.obrazovanju.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KorisnikOglasID implements Serializable {

    @Column(name = "korsinikID")
    private Long korisnikID;

    @Column(name = "oglasID")
    private Long oglasID;

    public KorisnikOglasID(Long korisnikID, Long oglasID) {
        this.korisnikID = korisnikID;
        this.oglasID = oglasID;
    }

    public KorisnikOglasID(){}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if(o == null || getClass() !=o.getClass())
            return  false;

        KorisnikOglasID that = (KorisnikOglasID) o;
        return Objects.equals(korisnikID, that.korisnikID) && Objects.equals(oglasID, that.oglasID);
    }

    @Override
    public int hashCode(){
        return Objects.hash(korisnikID,oglasID);
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public Long getOglasID() {
        return oglasID;
    }

    public void setOglasID(Long oglasID) {
        this.oglasID = oglasID;
    }
}
