package evidencijazadataka.service;

import java.util.List;

import org.springframework.data.domain.Page;

import evidencijazadataka.model.Zadatak;

public interface ZadatakService {
	
	Zadatak findOne(Long id);
	
	List<Zadatak> findAll();
	
	Zadatak save(Zadatak zadatak);
	
	Zadatak update(Zadatak zadatak);
	
	Zadatak delete(Long id);
	
	Page<Zadatak> pretraga(String ime, Long sprintId, int pageNo);

}
