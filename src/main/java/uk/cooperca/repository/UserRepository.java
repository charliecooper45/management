package uk.cooperca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.cooperca.entity.User;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link User}s.
 *
 * @author Charlie Cooper
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUsername(String login);
}
