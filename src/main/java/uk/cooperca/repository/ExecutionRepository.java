package uk.cooperca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.cooperca.entity.Execution;

/**
 * Spring Data JPA repository for {@link Execution}s.
 *
 * @author Charlie Cooper
 */
public interface ExecutionRepository extends JpaRepository<Execution, Long> {
}
