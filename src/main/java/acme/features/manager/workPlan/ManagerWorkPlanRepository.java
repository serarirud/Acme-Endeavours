package acme.features.manager.workPlan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkPlanRepository extends AbstractRepository {
	
	@Query("SELECT w FROM WorkPlan w WHERE w.id = ?1")
	WorkPlan findOneById(int id);
	
	@Query("SELECT w FROM WorkPlan w")
	Collection<WorkPlan> findMany();
}
