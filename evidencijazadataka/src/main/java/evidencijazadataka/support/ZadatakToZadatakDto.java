package evidencijazadataka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import evidencijazadataka.dto.ZadatakDTO;
import evidencijazadataka.model.Zadatak;


@Component
public class ZadatakToZadatakDto implements Converter<Zadatak, ZadatakDTO>{

	
	
	@Autowired
	private StanjeToStanjeDto stanjeToStanjeDto;
	
	@Autowired
	private SprintToSprintDto sprintToSprintDto;
	
	@Override
	public ZadatakDTO convert(Zadatak source) {
		ZadatakDTO zadatakDTO = new ZadatakDTO();
		zadatakDTO.setId(source.getId());
		zadatakDTO.setIme(source.getIme());
		zadatakDTO.setZaduzeni(source.getZaduzeni());
		zadatakDTO.setBodovi(source.getBodovi());
		zadatakDTO.setSprintDTO(sprintToSprintDto.convert(source.getSprint()));
		zadatakDTO.setStanjeDTO(stanjeToStanjeDto.convert(source.getStanje()));
	
		return zadatakDTO;
	}
	
	public List<ZadatakDTO> convert(List<Zadatak> zadaci){
		List<ZadatakDTO> zadaciDTO = new ArrayList<>();
		
		for(Zadatak z:zadaci) {
			zadaciDTO.add(convert(z));
		}
		
		return zadaciDTO;
	}

}
