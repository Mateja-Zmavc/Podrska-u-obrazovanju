package THE.Group.Podrska.u.obrazovanju.models;

import java.util.Set;

public class UstanovaDto {

    private Integer ustanoveID;

    private String naziv;

    private String email;

    private String username;

    private String password;

    private TipKorisnika uloge;

    private Set<MailingLista> lista;

    private  Set<Oglas> oglas;

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
        return oglas;
    }

    public void setOglasi(Set<Oglas> oglas) {
        this.oglas = oglas;
    }


}
