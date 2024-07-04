package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a theme of Dissertation.
 */
@Entity
public class Dissertation extends Theme {

  /** Protected constructor required by JPA. */
  protected Dissertation() {}

  /**
   * Creates a new instance of a Dissertation with the specified {@code title}, and {@code Teacher}.
   *
   * @param title The title of this dissertation.
   * @param advisor The advisor of this dissertation.
   */
  public Dissertation(@NonNull String title, @NonNull Teacher advisor) {
    super(title, advisor);
  }

  @Override
  public String toString() {
    return "Dissertation[title=" + getTitle() + ", advisor=" + getAdvisor() + "]";
  }
}
