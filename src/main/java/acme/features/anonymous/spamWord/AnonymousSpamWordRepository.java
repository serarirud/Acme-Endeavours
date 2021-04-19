package acme.features.anonymous.spamWord;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.spamWord.SpamWord;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousSpamWordRepository extends AbstractRepository{

	@Query("select s from SpamWord s")
	List<SpamWord> findMany();
}
