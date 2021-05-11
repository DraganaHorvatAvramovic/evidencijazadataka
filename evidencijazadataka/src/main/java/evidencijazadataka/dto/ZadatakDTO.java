package evidencijazadataka.dto;

public class ZadatakDTO {
	
	private Long id;
	
	private String ime;
	
	private String zaduzeni;

	private int bodovi;
	
	private SprintDTO sprintDTO;
	
	private StanjeDTO stanjeDTO;

	public ZadatakDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getZaduzeni() {
		return zaduzeni;
	}

	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}

	public int getBodovi() {
		return bodovi;
	}

	public void setBodovi(int bodovi) {
		this.bodovi = bodovi;
	}

	public SprintDTO getSprintDTO() {
		return sprintDTO;
	}

	public void setSprintDTO(SprintDTO sprintDTO) {
		this.sprintDTO = sprintDTO;
	}

	public StanjeDTO getStanjeDTO() {
		return stanjeDTO;
	}

	public void setStanjeDTO(StanjeDTO stanjeDTO) {
		this.stanjeDTO = stanjeDTO;
	}
	
	
	
	
}
