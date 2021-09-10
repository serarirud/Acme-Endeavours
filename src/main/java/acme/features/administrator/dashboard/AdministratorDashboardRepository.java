/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (c) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(t) FROM Task t WHERE (t.isPublic = true)")
	Integer nPublicTask();
	
	@Query("SELECT count(t) FROM Task t WHERE (t.isPublic = false)")
	Integer nPrivateTask();
	
	@Query("SELECT count(t) FROM Task t WHERE (t.endExecutionPeriod > :today)")
	Integer nNotFinishedTask(Date today);

	@Query("SELECT count(t) FROM Task t WHERE (t.endExecutionPeriod < :today)")
	Integer nFinishedTask(Date today);

	//DATEDIFF calcula la diferencia en días entre 2 fechas
	@Query("select avg(DATEDIFF(t.endExecutionPeriod, t.startExecutionPeriod))  from Task t")
	Double averageTaskExecutionPeriods();

	@Query("select stddev(DATEDIFF(t.endExecutionPeriod, t.startExecutionPeriod))  from Task t")
	Double deviationTaskExecutionPeriods();

	@Query("select min(DATEDIFF(t.endExecutionPeriod, t.startExecutionPeriod))  from Task t")
	Integer minimumTaskExecutionPeriods();

	@Query("select max(DATEDIFF(t.endExecutionPeriod, t.startExecutionPeriod))  from Task t")
	Integer maximumTaskExecutionPeriods();

	@Query("select t from Task t")
	Collection<Task> findAllTask();
	
	@Query("select min(t.workload)  from Task t")
	Double minimumTaskWorkloads();

	@Query("select max(t.workload)  from Task t")
	Double maximumTaskWorkloads();
	
	@Query("select count(s) from Shout s")
	Integer shouts();
	
	@Query("select count(s) from Shout s where s.pustemi.important = true")
	Integer shoutsFlaggedAsImportant();
	
	@Query("select count(s) from Shout s where s.pustemi.budget.amount = 0")
	Integer shoutsWithBudgetZero();
	
	@Query("select avg(s.pustemi.budget.amount)  from Shout s where s.pustemi.budget.currency = :currency")
	Double averageShoutsGroupedByCurrency(String currency);

	@Query("select stddev(s.pustemi.budget.amount)  from Shout s where s.pustemi.budget.currency = :currency")
	Double deviationShoutsGroupedByCurrency(String currency);

	
}
