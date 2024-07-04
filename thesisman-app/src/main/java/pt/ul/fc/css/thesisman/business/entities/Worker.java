package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.Entity;
import org.springframework.lang.NonNull;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a Worker.
 */
@Entity
public class Worker extends GeneralUser {

  @NonNull
  private String company;

  /**
   * Protected constructor required by JPA, initializes all {@code @NonNull} annotated attributes
   * with default values.
   */
  protected Worker() {
    this.company = "company";
  }

  /**
   * Creates a new instance of a Worker with the specified username, password, name and company.
   *
   * @param username The username of the worker.
   * @param password The password of the worker.
   * @param name The name of the worker.
   * @param company The company of the worker.
   */
  public Worker(
      @NonNull String username,
      @NonNull String password,
      @NonNull String name,
      @NonNull String company) {
    super(username, password, name);
    this.company = company;
  }

  /**
   * Returns the company of this worker.
   *
   * @return The company of this worker.
   */
  public @NonNull String getCompany() {
    return company;
  }

  /**
   * Sets the company for this worker.
   *
   * @param company The company given by the worker to replace the old company.
   */
  public void setCompany(@NonNull String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "Worker["
        + "username="
        + getUsername()
        + ", "
        + "name="
        + getName()
        + ", "
        + "pass="
        + getPassword()
        + ", "
        + "company="
        + company
        + "]";
  }
}
