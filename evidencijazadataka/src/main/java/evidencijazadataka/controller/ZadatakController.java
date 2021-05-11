package evidencijazadataka.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import evidencijazadataka.dto.ZadatakDTO;
import evidencijazadataka.model.Zadatak;
import evidencijazadataka.service.StanjeService;
import evidencijazadataka.service.ZadatakService;
import evidencijazadataka.support.ZadatakDtoToZadatak;
import evidencijazadataka.support.ZadatakToZadatakDto;

@RestController
@RequestMapping(value = "/api/zadaci",produces = MediaType.APPLICATION_JSON_VALUE)
public class ZadatakController {

	@Autowired
	private ZadatakService zadatakService;
	
	@Autowired
	private ZadatakToZadatakDto toZadatakDto;
	
	@Autowired
	private ZadatakDtoToZadatak toZadatak;
	
	@Autowired
	private StanjeService stanjeService;
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<ZadatakDTO>> getAll(
			@RequestParam(required=false) String ime,
			@RequestParam(required=false) Long sprintId,
			@RequestParam(defaultValue = "0") int pageNo){
		
		Page<Zadatak> zadatakPage = zadatakService.pretraga(ime, sprintId, pageNo);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Total-Pages", zadatakPage.getTotalPages() + "");
		
		return new ResponseEntity<>(toZadatakDto.convert(zadatakPage.getContent()), responseHeaders, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> update(@PathVariable Long id,  @Valid @RequestBody ZadatakDTO zadatakDTO){
		
		if(!id.equals(zadatakDTO.getId())) {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Zadatak zadatak = toZadatak.convert(zadatakDTO);
		Zadatak sacuvanZadatak = zadatakService.update(zadatak);
		
		return new ResponseEntity<>(toZadatakDto.convert(sacuvanZadatak), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<ZadatakDTO> getOne(@PathVariable Long id){
		
		Zadatak zadatak = zadatakService.findOne(id);
		
		if(zadatak != null) {
			return new ResponseEntity<>(toZadatakDto.convert(zadatak), HttpStatus.OK);
		}else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> create (@Valid @RequestBody ZadatakDTO zadatakDTO){
		Zadatak zadatak = toZadatak.convert(zadatakDTO);
		Zadatak sacuvanZadatak = zadatakService.save(zadatak);
		
		return new ResponseEntity<>(toZadatakDto.convert(sacuvanZadatak), HttpStatus.CREATED);
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Zadatak> delete(@PathVariable Long id){
		Zadatak obrisanZadatak = zadatakService.delete(id);
		
		if(obrisanZadatak != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}/promenastanja", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ZadatakDTO> promenastanja(@PathVariable Long id){
		Zadatak zadatak = zadatakService.findOne(id);
		
		if(zadatak.getStanje().getIme().equals("nov")) {
			zadatak.setStanje(stanjeService.findOne(2L));
		} else if (zadatak.getStanje().getIme().equals("u-toku")) {
			zadatak.setStanje(stanjeService.findOne(1L));
		}
		
		zadatak = zadatakService.update(zadatak);
		
		return new ResponseEntity<>(toZadatakDto.convert(zadatak), HttpStatus.OK);
	}
 }
