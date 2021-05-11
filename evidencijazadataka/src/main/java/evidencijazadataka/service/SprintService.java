package evidencijazadataka.service;

import java.util.List;

import evidencijazadataka.model.Sprint;

public interface SprintService {
	
	Sprint findOne(Long id);
	
	List<Sprint> findAll();
	
	Sprint save(Sprint sprint);
	
	Sprint update(Sprint sprint);
	
	Sprint delete(Long id);

}
