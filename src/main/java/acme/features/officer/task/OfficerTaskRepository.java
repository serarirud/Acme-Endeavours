package acme.features.officer.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.entities.Officer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface OfficerTaskRepository extends AbstractRepository {

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findOneById(int id);
	
	@Query("SELECT t FROM Task t WHERE t.officer.id = ?1")
	Collection<Task> findMany(int idManager);
	
	@Query("select o from Officer o where o.id = ?1")
	Officer findOneManagerById(int id);
	
}
