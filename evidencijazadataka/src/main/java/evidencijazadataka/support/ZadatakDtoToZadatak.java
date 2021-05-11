package evidencijazadataka.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import evidencijazadataka.dto.ZadatakDTO;
import evidencijazadataka.model.Zadatak;
import evidencijazadataka.service.SprintService;
import evidencijazadataka.service.StanjeService;
import evidencijazadataka.service.ZadatakService;

@Component
public class ZadatakDtoToZadatak implements Converter<ZadatakDTO, Zadatak>{
	
	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private StanjeService stanjeService;
	
	@Autowired
	private SprintService sprintService;
	
	@Override
	public Zadatak convert(ZadatakDTO source) {
		Zadatak zadatak;
		
		if(source.getId() == null) {
			zadatak = new Zadatak();
		} else {
			zadatak = zadatakService.findOne(source.getId());
		}
		
		if(zadatak != null) {
			zadatak.setIme(source.getIme());
			zadatak.setZaduzeni(source.getZaduzeni());
			zadatak.setBodovi(source.getBodovi());
			if(source.getSprintDTO() != null) {
				zadatak.setSprint(sprintService.findOne(source.getSprintDTO().getId()));
			}
			if(source.getStanjeDTO() != null) {
				zadatak.setStanje(stanjeService.findOne(source.getStanjeDTO().getId()));
			}
		}
		
		return zadatak;
	}

}
