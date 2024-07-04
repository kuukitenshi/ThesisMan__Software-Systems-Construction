package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Student;

import java.util.List;
import java.util.Optional;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind This interface represent a JpaRepository for a Student.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {

  /**
   * Finds the student with the given username.
   *
   * @return A list containing all users from the given username.
   */
  Optional<Student> findByUsername(String username);

  @Query("SELECT s FROM Student s WHERE NOT EXISTS (SELECT t FROM Thesis t WHERE t.student = s)")
  List<Student> findWithoutThesis();
}
