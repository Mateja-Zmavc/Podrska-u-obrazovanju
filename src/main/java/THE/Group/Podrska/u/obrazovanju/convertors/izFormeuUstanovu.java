package THE.Group.Podrska.u.obrazovanju.convertors;

import THE.Group.Podrska.u.obrazovanju.models.Ustanova;
import THE.Group.Podrska.u.obrazovanju.models.UstanovaDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class izFormeuUstanovu implements Converter<UstanovaDto, Ustanova> {

    @Override
    public Ustanova convert(UstanovaDto ustanovaDto) {
        Ustanova ustanova = new Ustanova();
        if (ustanovaDto.getUstanoveID() != null  && !StringUtils.isEmpty(ustanovaDto.getUstanoveID())) {
            ustanova.setUstanoveID(new Integer(ustanovaDto.getUstanoveID()));
        }
        ustanova.setUstanoveID(ustanovaDto.getUstanoveID());
        ustanova.setNaziv(ustanovaDto.getNaziv());
        ustanova.setEmail(ustanovaDto.getEmail());
        ustanova.setUsername(ustanovaDto.getUsername());
        ustanova.setPassword(ustanovaDto.getPassword());
        ustanova.setUloge(ustanovaDto.getUloge());
        ustanova.setLista(ustanovaDto.getLista());
        ustanova.setOglasi(ustanovaDto.getOglasi());
        return ustanova;
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
