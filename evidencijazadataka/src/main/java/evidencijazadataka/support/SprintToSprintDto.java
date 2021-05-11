package evidencijazadataka.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import evidencijazadataka.dto.SprintDTO;
import evidencijazadataka.model.Sprint;

@Component
public class SprintToSprintDto implements Converter<Sprint, SprintDTO>{

	@Override
	public SprintDTO convert(Sprint source) {
		SprintDTO dto = new SprintDTO();
		
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setBodovi(source.getBodovi());
		
		return dto;
	}
	
	public List<SprintDTO> convert(List<Sprint> sprintovi) {
		List<SprintDTO> sprintoviDTO = new ArrayList<>();
		
		for (Sprint s:sprintovi) {
			sprintoviDTO.add(convert(s));
		}
		
		return sprintoviDTO;
	}

}
