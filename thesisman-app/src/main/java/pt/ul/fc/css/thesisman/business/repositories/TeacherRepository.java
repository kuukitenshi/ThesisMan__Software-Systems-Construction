package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Teacher;

import java.util.Optional;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind This interface represent a JpaRepository for a Teacher.
 */
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

  /**
   * Finds the teacher with the given username.
   *
   * @return A list containing all users from the given username.
   */
  Optional<Teacher> findByUsername(String username);
}
