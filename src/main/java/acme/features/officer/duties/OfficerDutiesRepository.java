package acme.features.officer.duties;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duties;
import acme.framework.entities.Officer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerDutiesRepository extends AbstractRepository {

	@Query("SELECT d FROM Duties d WHERE d.id = ?1")
	Duties findOneById(int id);
	
	@Query("SELECT d FROM Duties d WHERE d.officer.id = ?1")
	Collection<Duties> findMany(int idManager);
	
	@Query("select o from Officer o where o.id = ?1")
	Officer findOneOfficerById(int id);
	
}
