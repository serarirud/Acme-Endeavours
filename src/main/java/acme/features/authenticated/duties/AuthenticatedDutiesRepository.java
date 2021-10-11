package acme.features.authenticated.duties;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duties;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDutiesRepository extends AbstractRepository {

	//tareas públicas y que no estén finalizadas
	@Query("SELECT d FROM Duties d WHERE (d.isPublic = true AND d.endExecutionPeriod < :today) ")
	Collection<Duties> findMany(Date today);
	
	@Query("SELECT d FROM Duties d WHERE d.id = ?1")
	Duties findOneById(int id);
	
}
