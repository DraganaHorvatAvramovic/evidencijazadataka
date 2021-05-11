package evidencijazadataka.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import evidencijazadataka.dto.SprintDTO;
import evidencijazadataka.model.Sprint;
import evidencijazadataka.service.SprintService;


@Component
public class SprintDtoToSprint implements Converter<SprintDTO, Sprint>{
	
	@Autowired
	private SprintService sprintService;
	
	
 
	@Override
	public Sprint convert(SprintDTO source) {
		Sprint sprint;
		
		if(source.getId() == null) {
			sprint = new Sprint();
		} else {
			sprint = sprintService.findOne(source.getId());
		}
		
		if(sprint != null) {
			sprint.setIme(source.getIme());
			sprint.setBodovi(source.getBodovi());
		}
		return sprint;
	}

}
