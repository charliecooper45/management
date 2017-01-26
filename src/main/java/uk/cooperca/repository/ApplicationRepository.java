package uk.cooperca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.cooperca.entity.Application;

/**
 * Spring Data JPA repository for {@link Application}s.
 *
 * @author Charlie Cooper
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
