package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.springframework.lang.NonNull;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a Student.
 */
@Entity
public class Student extends GeneralUser {

  @NonNull
  @ManyToOne private MastersDegree mastersDegree;

  private Float grade;

  /**
   * Protected constructor required by JPA, initializes all {@code @NonNull} annotated attributes
   * with default values.
   */
  protected Student() {
    this.mastersDegree = new MastersDegree();
  }

  /**
   * Creates a new instance of a Student with the specified username, password, name and master's
   * degree.
   *
   * @param username The username of the student.
   * @param password The password of the student.
   * @param name The name of the student.
   * @param mastersDegree The master's degree of the student.
   */
  public Student(
      @NonNull String username,
      @NonNull String password,
      @NonNull String name,
      @NonNull MastersDegree mastersDegree) {
    super(username, password, name);
    this.mastersDegree = mastersDegree;
  }

  /**
   * Returns the master's degree of this student.
   *
   * @return The master's degree of this student.
   */
  public @NonNull MastersDegree getMastersDegree() {
    return this.mastersDegree;
  }

  /**
   * Sets the master's degree for this student.
   *
   * @param mastersDegree The master's degree given by the student.
   */
  public void setMastersDegree(@NonNull MastersDegree mastersDegree) {
    this.mastersDegree = mastersDegree;
  }

  /**
   * Returns the grade of this student.
   *
   * @return The grade of this student.
   */
  public Float getGrade() {
    return this.grade;
  }

  /**
   * Sets the grade for this student.
   *
   * @param grade The grade given by the student.
   */
  public void setGrade(Float grade) {
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Student["
        + "username="
        + getUsername()
        + ", "
        + "name="
        + getName()
        + ", "
        + "pass="
        + getPassword()
        + ", "
        + "mastersDegree="
        + mastersDegree
        + "]";
  }
}
