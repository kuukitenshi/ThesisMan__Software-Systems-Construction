package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Theme;

import java.util.List;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This interface represent a JpaRepository for a Theme.
 */
public interface ThemeRepository extends CrudRepository<Theme, Long> {

  /**
   * Finds all themes for a given year.
   *
   * @return A list containing all themes from the given year.
   */
  List<Theme> findByYear(String year);
}
