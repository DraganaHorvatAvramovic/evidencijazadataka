package evidencijazadataka.service;

import java.util.List;

import evidencijazadataka.model.Stanje;

public interface StanjeService {
	
	Stanje findOne(Long id);
	
	List<Stanje> findAll();
	
	Stanje save(Stanje stanje);
	
	Stanje update(Stanje stanje);
	
	Stanje delete(Long id);

}
