package evidencijazadataka.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evidencijazadataka.model.Stanje;
import evidencijazadataka.repository.StanjeRepository;
import evidencijazadataka.service.StanjeService;

@Service
public class JpaStanjeService implements StanjeService{

	@Autowired
	private StanjeRepository stanjeRepository;
	
	@Override
	public Stanje findOne(Long id) {
		return stanjeRepository.findOneById(id);
	}

	@Override
	public List<Stanje> findAll() {
		return stanjeRepository.findAll();
	}

	@Override
	public Stanje save(Stanje stanje) {
		return stanjeRepository.save(stanje);
	}

	@Override
	public Stanje update(Stanje stanje) {
		return stanjeRepository.save(stanje);
	}

	@Override
	public Stanje delete(Long id) {
		Optional<Stanje> stanje = stanjeRepository.findById(id);
		if(stanje.isPresent()) {
			stanjeRepository.deleteById(id);
			return stanje.get();
		}
		return null;
	}

}
