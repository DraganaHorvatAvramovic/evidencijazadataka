package evidencijazadataka.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import evidencijazadataka.dto.StanjeDTO;
import evidencijazadataka.model.Stanje;
import evidencijazadataka.service.StanjeService;

@Component
public class StanjeDtoToStanje implements Converter<StanjeDTO, Stanje>{

	@Autowired
	private StanjeService stanjeService;
	
	@Override
	public Stanje convert(StanjeDTO source) {
		Stanje stanje;
		
		if(source.getId() == null) {
			stanje = new Stanje();
		} else {
			stanje = stanjeService.findOne(source.getId());
		}
		
		if(stanje != null) {
			stanje.setIme(source.getIme());
		}
		return stanje;
	}

}
