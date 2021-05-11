package evidencijazadataka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import evidencijazadataka.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
	
	Sprint findOneById(Long id);
}
