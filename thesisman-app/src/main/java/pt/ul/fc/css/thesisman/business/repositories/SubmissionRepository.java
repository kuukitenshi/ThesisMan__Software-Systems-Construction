package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Submission;

import java.util.Optional;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind This interface represent a JpaRepository for a Submission.
 */
public interface SubmissionRepository extends CrudRepository<Submission, Long> {

  Optional<Submission> findSubmissionByDefenceId(Long defenceId);

}
