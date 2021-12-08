package acme.features.anonymous.info;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.info.Info;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
	
	@Query("select i from Info i where i.shout.moment >= ?1")
	Collection<Info> findMany(Date deadline);

	//################################################ CAMBIAR ################################################################	
	@Query("select i from Info i where i.pattern = ?1")
	Optional<Info> findSheetByPattern(String pattern);
	//#########################################################################################################################	
	
}
