package acme.features.administrator.workPlanData;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import acme.entities.workPlan.WorkPlan;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorWorkPlanRepository extends AbstractRepository {

	@Query("SELECT wp FROM WorkPlan wp")
	Set<WorkPlan> findAllWorkPlans();
}
