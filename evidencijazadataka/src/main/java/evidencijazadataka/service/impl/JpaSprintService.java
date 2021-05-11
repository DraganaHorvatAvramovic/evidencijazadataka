package evidencijazadataka.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evidencijazadataka.model.Sprint;
import evidencijazadataka.repository.SprintRepository;
import evidencijazadataka.service.SprintService;

@Service
public class JpaSprintService implements SprintService{

	@Autowired
	private SprintRepository sprintRepository;
	
	@Override
	public Sprint findOne(Long id) {
		return sprintRepository.findOneById(id);
	}

	@Override
	public List<Sprint> findAll() {
		return sprintRepository.findAll();
	}

	@Override
	public Sprint save(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint update(Sprint sprint) {
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint delete(Long id) {
		Optional<Sprint> sprint = sprintRepository.findById(id);
		if(sprint.isPresent()) {
			sprintRepository.deleteById(id);
			return sprint.get();
		}
		return null;
	}

}
