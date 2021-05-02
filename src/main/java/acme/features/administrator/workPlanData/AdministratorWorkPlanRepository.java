package acme.features.administrator.workPlanData;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorWorkPlanRepository extends AbstractRepository {

	@Query("SELECT wp FROM WorkPlan wp")
	Set<WorkPlan> findAllWorkPlans();
	
	@Query("SELECT count(wp) FROM WorkPlan wp")
	Integer workPlanAmount();
	
	@Query("SELECT count(wp) FROM WorkPlan wp WHERE wp.endExecutionPeriod < ?1")
	Integer finishedWorkPlanAmount(Date now);
	
	@Query("SELECT count(wp) FROM WorkPlan wp WHERE wp.isPublished = 1")
	Integer publishedWorkPlanAmount();
	
	@Query("select avg(DATEDIFF(wp.endExecutionPeriod, wp.startExecutionPeriod))  from WorkPlan wp")
	Double averageExecutionPeriods();
	
	@Query("select stddev(DATEDIFF(wp.endExecutionPeriod, wp.startExecutionPeriod))  from WorkPlan wp")
	Double deviationExecutionPeriods();
}
