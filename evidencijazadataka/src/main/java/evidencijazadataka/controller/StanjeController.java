package evidencijazadataka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import evidencijazadataka.dto.StanjeDTO;
import evidencijazadataka.model.Stanje;
import evidencijazadataka.service.StanjeService;
import evidencijazadataka.support.StanjeToStanjeDto;



@RestController
@RequestMapping(value = "/api/stanja", produces = MediaType.APPLICATION_JSON_VALUE)
public class StanjeController {
	
	@Autowired
	private StanjeToStanjeDto toStanjeDto;
	
	@Autowired
	private StanjeService stanjeService;

	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<StanjeDTO>> getAll(){
		List<Stanje> stanja = stanjeService.findAll();
		
		return new ResponseEntity<>(toStanjeDto.convert(stanja), HttpStatus.OK);
	}
	
}
