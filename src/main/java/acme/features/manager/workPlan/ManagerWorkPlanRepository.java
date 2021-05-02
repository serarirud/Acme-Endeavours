package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.entities.workPlan.WorkPlan;
import acme.framework.entities.Manager;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository {
	
	@Query("SELECT w FROM WorkPlan w JOIN FETCH w.tasks t WHERE w.id = ?1")
	WorkPlan findOneById(int id);
	
	@Query("SELECT w FROM WorkPlan w JOIN FETCH w.tasks t WHERE w.manager.id = ?1")
	Collection<WorkPlan> findMany(int id);
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
	@Query("SELECT t FROM Task t WHERE t.manager.id = ?1")
	Collection<Task> findTasks(int managerId);
}
