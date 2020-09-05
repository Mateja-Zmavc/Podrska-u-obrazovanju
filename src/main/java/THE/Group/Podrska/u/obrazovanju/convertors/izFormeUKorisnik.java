package THE.Group.Podrska.u.obrazovanju.convertors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import THE.Group.Podrska.u.obrazovanju.models.Korisnik;
import THE.Group.Podrska.u.obrazovanju.models.RegisterDto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class izFormeUKorisnik implements Converter<RegisterDto, Korisnik> {
    @Override
    public Korisnik convert(RegisterDto registerDto) {
        Korisnik korisnik = new Korisnik();
        if (registerDto.getKorisnikID() != null  && !StringUtils.isEmpty(registerDto.getKorisnikID())) {
            korisnik.setKorisnikID(new Long(registerDto.getKorisnikID()));
        }
        korisnik.setIme(registerDto.getIme());
        korisnik.setPrezime(registerDto.getPrezime());
        korisnik.setUsername(registerDto.getUsername());
        korisnik.setPassword(registerDto.getPassword());
        korisnik.setEmail(registerDto.getEmail());
        korisnik.setOglasi(registerDto.getOglasi());
        korisnik.setUloge(registerDto.getUloge());
        return korisnik;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
