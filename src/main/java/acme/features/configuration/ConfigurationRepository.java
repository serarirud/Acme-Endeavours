package acme.features.configuration;

import org.springframework.data.jpa.repository.Query;

import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

public interface ConfigurationRepository extends AbstractRepository{

	@Query("select c from Configuration c")
	Configuration getConfiguration();
}
