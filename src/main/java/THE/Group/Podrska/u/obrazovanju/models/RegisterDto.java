package THE.Group.Podrska.u.obrazovanju.models;

import java.util.List;

public class RegisterDto {

    private Long korisnikID;

    private String ime;

    private String prezime;

    private String username;

    private String password;

    private String email;

    private TipKorisnika uloge;

    private List<KorisnikOglas> oglasi;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
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

    public void setUsername(String username) { this.username = username;  }

    public TipKorisnika getUloge() {
        return uloge;
    }

    public void setUloge(TipKorisnika uloge) {
        this.uloge = uloge;
    }

    public List<KorisnikOglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(List<KorisnikOglas> oglasi) {
        this.oglasi = oglasi;
    }
}
