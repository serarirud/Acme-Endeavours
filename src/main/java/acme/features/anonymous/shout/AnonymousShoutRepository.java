package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
	
	@Query("select s from Shout s where s.moment >= ?1")
	Collection<Shout> findMany(Date deadline);
	
	@Query("select s from Shout s where s.pustemi.label = ?1")
	Optional<Shout> findShoutByLabel(String label);

}
