package THE.Group.Podrska.u.obrazovanju.convertors;

import THE.Group.Podrska.u.obrazovanju.models.Korisnik;
import THE.Group.Podrska.u.obrazovanju.models.RegisterDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class izKorisnikUFormu implements Converter<Korisnik, RegisterDto> {
    @Override
    public RegisterDto convert(Korisnik korisnik) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setKorisnikID(korisnik.getKorisnikID());
        registerDto.setIme(korisnik.getIme());
        registerDto.setPrezime(korisnik.getPrezime());
        registerDto.setUsername(korisnik.getUsername());
        registerDto.setPassword(korisnik.getPassword());
        registerDto.setEmail(korisnik.getEmail());
        registerDto.setUloge(korisnik.getUloge());
        registerDto.setOglasi(korisnik.getOglasi());
        return registerDto;
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
