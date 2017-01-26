package uk.cooperca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.cooperca.entity.Script;

/**
 * Spring Data JPA repository for {@link Script}s.
 *
 * @author Charlie Cooper
 */
public interface ScriptRepository extends JpaRepository<Script, Long> {
}
