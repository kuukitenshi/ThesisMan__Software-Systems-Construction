package pt.ul.fc.css.thesisman.business.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.ul.fc.css.thesisman.business.entities.Defence;

import java.util.List;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This interface represent a JpaRepository for a Defence.
 */
public interface DefenceRepository extends CrudRepository<Defence, Long> {

  List<Defence> findByClassRoom_Id(Long classRoomId);

}
