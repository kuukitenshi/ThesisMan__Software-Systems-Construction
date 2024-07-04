package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Application;

import java.util.List;
import java.util.Optional;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This interface represent a JpaRepository for an Application.
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {

  List<Application> findByStudent_Id(Long studentId);

  Long countByStudent_Id(Long studentId);

  Optional<Application> findByStudent_IdAndTheme_Id(Long studentId, Long themeId);
}
