package uk.cooperca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.cooperca.entity.Operation;

/**
 * Spring Data JPA repository for {@link Operation}s.
 *
 * @author Charlie Cooper
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
