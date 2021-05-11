package evidencijazadataka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import evidencijazadataka.dto.StanjeDTO;
import evidencijazadataka.model.Stanje;

@Component
public class StanjeToStanjeDto implements Converter<Stanje, StanjeDTO>{

	@Override
	public StanjeDTO convert(Stanje source) {
		StanjeDTO dto = new StanjeDTO();
		
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		
		return dto;
	}
	
	public List<StanjeDTO> convert(List<Stanje> stanja){
		List<StanjeDTO> stanjaDTO = new ArrayList<>();
		
		for(Stanje s:stanja) {
			stanjaDTO.add(convert(s));
		}
		
		return stanjaDTO;
	}

}
