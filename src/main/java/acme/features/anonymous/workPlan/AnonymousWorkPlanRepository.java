package acme.features.anonymous.workPlan;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkPlanRepository extends AbstractRepository{
	
	@Query("SELECT w FROM WorkPlan w WHERE w.id = ?1")
	WorkPlan findOneById(int id);
	
	//WorkPlans públicos y que no estén finalizados
	@Query("SELECT w FROM WorkPlan w WHERE (w.isPublic = true AND w.endExecutionPeriod > :today) ")
	Collection<WorkPlan> findMany(Date today);
}
