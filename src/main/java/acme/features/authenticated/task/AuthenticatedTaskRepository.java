package acme.features.authenticated.task;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository {

	//tareas públicas y que no estén finalizadas
	@Query("SELECT t FROM Task t WHERE (t.isPublic = true AND t.endExecutionPeriod < :today) ")
	Collection<Task> findMany(Date today);
	
	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneById(int id);
	
}
