package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a Teacher.
 */
@Entity
public class Teacher extends GeneralUser {

  /**
   * Protected constructor required by JPA, initializes all {@code @NonNull} annotated attributes
   */
  protected Teacher() {}

  /**
   * Creates a new instance of a Teacher with the specified username, password and name.
   *
   * @param username The username of the teacher.
   * @param password The password of the teacher.
   * @param name The name of the teacher.
   */
  public Teacher(@NonNull String username, @NonNull String password, @NonNull String name) {
    super(username, password, name);
  }

  @Override
  public String toString() {
    return "Teacher["
        + "username="
        + getUsername()
        + ", "
        + "name="
        + getName()
        + ", "
        + "pass="
        + getPassword()
        + "]";
  }
}
