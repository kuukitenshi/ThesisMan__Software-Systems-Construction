package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Worker;

import java.util.Optional;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This interface represent a JpaRepository for a Worker.
 */
public interface WorkerRepository extends CrudRepository<Worker, Long> {

  /**
   * Finds the worker with the given username.
   *
   * @return A list containing all users from the given username.
   */
  Optional<Worker> findByUsername(String username);
}
