package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.ul.fc.css.thesisman.business.entities.Thesis;

import java.util.List;
import java.util.Optional;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This interface represent a JpaRepository for a Thesis.
 */
public interface ThesisRepository extends CrudRepository<Thesis, Long> {

  List<Thesis> findByTheme_Advisor_Id(Long advisorId);

  Optional<Thesis> findByStudent_Id(Long studentId);

  @Query("SELECT t from Thesis t JOIN t.submissions s WHERE s.id = :submissionId")
  Optional<Thesis> findThesisBySubmissionId(@Param("submissionId") Long submissionId);
}
