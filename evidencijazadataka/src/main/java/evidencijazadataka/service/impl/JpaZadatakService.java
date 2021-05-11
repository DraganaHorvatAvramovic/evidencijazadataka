package evidencijazadataka.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import evidencijazadataka.model.Zadatak;
import evidencijazadataka.repository.ZadatakRepository;
import evidencijazadataka.service.ZadatakService;

@Service
public class JpaZadatakService implements ZadatakService{

	@Autowired
	private ZadatakRepository zadatakRepository;
	
	@Override
	public Zadatak findOne(Long id) {
		return zadatakRepository.findOneById(id);
	}

	@Override
	public List<Zadatak> findAll() {
		return zadatakRepository.findAll();
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak update(Zadatak zadatak) {
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		Optional<Zadatak> zadatak = zadatakRepository.findById(id);
		if(zadatak.isPresent()) {
			zadatakRepository.deleteById(id);
			return zadatak.get();
		}
		return null;
	}

	@Override
	public Page<Zadatak> pretraga(String ime, Long sprintId, int pageNo) {
		return zadatakRepository.pretraga(ime, sprintId, PageRequest.of(pageNo, 2));
	}

}
