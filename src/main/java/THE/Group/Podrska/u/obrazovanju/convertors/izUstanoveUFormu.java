package THE.Group.Podrska.u.obrazovanju.convertors;

import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.models.UstanovaDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class izUstanoveUFormu implements Converter<Ustanova, UstanovaDto> {
    @Override
    public UstanovaDto convert(Ustanova ustanova) {
        UstanovaDto ustanovaDto = new UstanovaDto();
        ustanovaDto.setUstanoveID(ustanova.getUstanoveID());
        ustanovaDto.setNaziv(ustanova.getNaziv());
        ustanovaDto.setEmail(ustanova.getEmail());
        ustanovaDto.setUsername(ustanova.getUsername());
        ustanovaDto.setPassword(ustanova.getPassword());
        ustanovaDto.setUloge(ustanova.getUloge());
        ustanovaDto.setLista(ustanova.getLista());
        ustanovaDto.setOglasi(ustanova.getOglasi());
        return ustanovaDto;
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
