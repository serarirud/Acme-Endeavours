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

import acme.entities.duties.Duties;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(d) FROM Duties d WHERE (d.isPublic = true)")
	Integer nPublicDuties();
	
	@Query("SELECT count(d) FROM Duties d WHERE (d.isPublic = false)")
	Integer nPrivateDuties();
	
	@Query("SELECT count(d) FROM Duties d WHERE (d.endExecutionPeriod > :today)")
	Integer nNotFinishedDuties(Date today);

	@Query("SELECT count(d) FROM Duties d WHERE (d.endExecutionPeriod < :today)")
	Integer nFinishedDuties(Date today);

	//DATEDIFF calcula la diferencia en días entre 2 fechas
	@Query("select avg(DATEDIFF(d.endExecutionPeriod, d.startExecutionPeriod))  from Duties d")
	Double averageDutiesExecutionPeriods();

	@Query("select stddev(DATEDIFF(d.endExecutionPeriod, d.startExecutionPeriod))  from Duties d")
	Double deviationDutiesExecutionPeriods();

	@Query("select min(DATEDIFF(d.endExecutionPeriod, d.startExecutionPeriod))  from Duties d")
	Integer minimumDutiesExecutionPeriods();

	@Query("select max(DATEDIFF(d.endExecutionPeriod, d.startExecutionPeriod))  from Duties d")
	Integer maximumDutiesExecutionPeriods();

	@Query("select d from Duties d")
	Collection<Duties> findAllDuties();
	
	@Query("select min(d.workload)  from Duties d")
	Double minimumDutiesWorkloads();

	@Query("select max(d.workload)  from Duties d")
	Double maximumDutiesWorkloads();
	
	//################################################ CAMBIAR ################################################################
	
	@Query("select count(s) from Shout s")
	Integer shoutAmount();
	
	@Query("select count(i) from Info i where i.important = true")
	Integer shoutsFlaggedAsImportant();
	
	@Query("select count(i) from Info i where i.money.amount = 0")
	Integer shoutsWhereBudgetIsZero();
	
	@Query("select avg(i.money.amount)  from Info i where i.money.currency = :currency")
	Double averageMoney(String currency);

	@Query("select stddev(i.money.amount)  from Info i where i.money.currency = :currency")
	Double deviationMoney(String currency);
	
	//#########################################################################################################################
	
}
