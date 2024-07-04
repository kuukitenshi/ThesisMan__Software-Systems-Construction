package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.springframework.lang.NonNull;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a theme of Project.
 */
@Entity
public class Project extends Theme {

  @NonNull
  @ManyToOne private Worker enterpriseAdvisor;

  /**
   * Protected constructor required by JPA, initializes all {@code @NonNull} annotated attributes
   * with default values.
   */
  protected Project() {
    this.enterpriseAdvisor = new Worker();
  }

  /**
   * Creates a new instance of a Project with the specified title, advisor and enterprise advisor.
   *
   * @param title The title of the project.
   * @param advisor The advisor of the project.
   * @param enterpriseAdvisor The enterprise advisor of the project.
   */
  public Project(
      @NonNull String title, @NonNull Teacher advisor, @NonNull Worker enterpriseAdvisor) {
    super(title, advisor);
    this.enterpriseAdvisor = enterpriseAdvisor;
  }

  /**
   * Returns the enterpriseAdvisor of this project.
   *
   * @return The enterprise advisor of this project.
   */
  public @NonNull Worker getEnterpriseAdvisor() {
    return enterpriseAdvisor;
  }

  /**
   * Sets the enterprise advisor for this project.
   *
   * @param enterpriseAdvisor The enterprise advisor given to this project.
   */
  public void setEnterpriseAdvisor(@NonNull Worker enterpriseAdvisor) {
    this.enterpriseAdvisor = enterpriseAdvisor;
  }

  @Override
  public String toString() {
    return "Project[title="
        + getTitle()
        + ", advisor="
        + getAdvisor()
        + ", enterpriseAdvisor="
        + enterpriseAdvisor
        + "]";
  }
}
