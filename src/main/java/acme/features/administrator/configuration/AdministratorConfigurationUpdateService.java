/*
 * AdministratorUserAccountUpdateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.features.configuration.ConfigurationRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ConfigurationRepository repository;

	// AbstractUpdateService<Administrator, UserAccount> interface -------------


	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamWords", "threshold");
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;

		Configuration result;
		result = this.repository.getConfiguration();

		return result;
	}

	@Override
	public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Configuration> request, final Configuration entity) {
		assert request != null;
		assert entity != null;
		
		final StringBuilder words = new StringBuilder();
		final String[] wordsArray = entity.getSpamWords().split(",");
		
		for(int i = 0; i< wordsArray.length; i++) {
			final String word = wordsArray[i].toLowerCase();
			final String end = i == wordsArray.length - 1 ? "" : ",";
			words.append(word.trim().replaceAll("\\s+", " ") + end);
		}
		
		entity.setSpamWords(words.toString());
		

		this.repository.save(entity);
	}

}
