package evidencijazadataka.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import evidencijazadataka.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long>{

	Zadatak findOneById(Long id);
	
	@Query("SELECT p FROM Zadatak p WHERE "
    		+ "(:ime IS NULL OR p.ime like %:ime%) AND "
    		+ "(:sprintId IS NULL OR p.sprint.id = :sprintId) ")
    Page<Zadatak> pretraga(@Param("ime") String ime, @Param("sprintId") Long sprintId, Pageable pageable);
}
