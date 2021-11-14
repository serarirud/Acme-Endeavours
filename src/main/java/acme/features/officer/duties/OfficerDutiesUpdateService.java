package acme.features.officer.duties;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duties;
import acme.features.configuration.ConfigurationService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Officer;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class OfficerDutiesUpdateService implements AbstractUpdateService<Officer, Duties>{

	
	@Autowired
	protected OfficerDutiesRepository repository;

	@Autowired
	protected ConfigurationService confService;
	
	@Override
	public boolean authorise(final Request<Duties> request) {
		assert request != null;
		
		int dutiesId;
		Duties duties;
		Officer officer;
		Principal principal;
		
		dutiesId = request.getModel().getInteger("id");
		duties = this.repository.findOneById(dutiesId);
		officer = duties.getOfficer();
		principal = request.getPrincipal();
		
		return officer.getUserAccount().getId() == principal.getAccountId();
	}

	@Override
	public void bind(final Request<Duties> request, final Duties entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duties> request, final Duties entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "startExecutionPeriod", "endExecutionPeriod", 
			"workload", "description", "link", "isPublic");	
	}

	@Override
	public Duties findOne(final Request<Duties> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		return this.repository.findOneById(id);
	}

	@Override
	public void validate(final Request<Duties> request, final Duties entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("startExecutionPeriod") && !errors.hasErrors("endExecutionPeriod")) {
			errors.state(request, entity.getEndExecutionPeriod().after(entity.getStartExecutionPeriod()), "endExecutionPeriod", "officer.duties.form.error.end");
		}
		
		if(!errors.hasErrors("workload")) {
			final Double workload = entity.getWorkload();
			final Integer parteEntera = workload.intValue();
			final Double parteDecimal = workload - parteEntera;
			errors.state(request, parteDecimal<0.6, "workload", "officer.duties.form.error.workload");
			
			if (!errors.hasErrors("startExecutionPeriod") && !errors.hasErrors("endExecutionPeriod")) {
				final Date startExecutionPeriod = entity.getStartExecutionPeriod();
				final Date endExecutionPeriod = entity.getEndExecutionPeriod();
				final long diferencia = endExecutionPeriod.getTime() - startExecutionPeriod.getTime();
				final Integer minutosDiferencia = (int) (diferencia/(1000*60));
				final Integer minutosWorkload = (int) (parteEntera*60 + parteDecimal*100);
				errors.state(request, minutosDiferencia>=minutosWorkload, "workload", "officer.duties.form.error.workload2");
			}

		}
		
		if(!errors.hasErrors("description") && !errors.hasErrors("title")) {
			final boolean umbralSuperado = this.confService.spamFilter(entity.getTitle()+" "+entity.getDescription());
			errors.state(request, !umbralSuperado,"description", "officer.duties.error.umbral-superado");
			errors.state(request, !umbralSuperado,"title", "officer.duties.error.umbral-superado");
		}
	}

	@Override
	public void update(final Request<Duties> request, final Duties entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
