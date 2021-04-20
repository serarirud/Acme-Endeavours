package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository {

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneById(int id);
	
	//tareas públicas y que no estén finalizadas
	@Query("SELECT t FROM Task t WHERE t.manager.id = ?1")
	Collection<Task> findMany(int idManager);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
}
