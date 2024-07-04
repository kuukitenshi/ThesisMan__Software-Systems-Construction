package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import pt.ul.fc.css.thesisman.business.enums.DefenceType;

import java.time.LocalDateTime;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a thesis defence.
 */
@Entity
public class Defence {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Float grade;

  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime dateTime;

  private int duration = 60;

  @NonNull
  @Enumerated(EnumType.STRING)
  private DefenceType type = DefenceType.IN_PERSON;

  @ManyToOne private ClassRoom classRoom;
  @ManyToOne private Teacher arguer;
  @ManyToOne private Teacher president;

  /** Protected constructor required by JPA. */
  protected Defence() {}

  /**
   * Creates a new instance of a Defence with the specified type.
   *
   * @param type The type of the defence to be created.
   */
  public Defence(@NonNull DefenceType type) {
    this.type = type;
  }

  /**
   * Returns the id of this defence.
   *
   * @return The id generated by the database for this defence.
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the grade given to this defence.
   *
   * @return The grade of this defence.
   */
  public Float getGrade() {
    return grade;
  }

  /**
   * Sets the grade for this defence.
   *
   * @param grade The grade given to the defence by some Teacher.
   */
  public void setGrade(Float grade) {
    this.grade = grade;
  }

  /**
   * Returns the datetime of this defence.
   *
   * @return The datetime of this defence.
   */
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  /**
   * Sets the datetime of this defence.
   *
   * @param dateTime The datetime the defence should be scheduled to.
   */
  public void setDateTime(LocalDateTime dateTime) {
    this.dateTime = dateTime;
  }

  /**
   * Returns the duration in minutes of this defence.
   *
   * @return Duration in minutes of the defence.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration in minutes of this defence.
   *
   * @param duration The duration in minutes this defence should last for.
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Returns the type of this defence.
   *
   * @return The type of the defence.
   */
  public @NonNull DefenceType getType() {
    return type;
  }

  /**
   * Returns the classroom where this defence will be done, this can be null if this defence doesn't
   * have a classroom reserved or the type is not {@code IN_PERSON}.
   *
   * @return The classroom for this defence or null.
   */
  public ClassRoom getClassRoom() {
    return this.classRoom;
  }

  /**
   * Returns the arguer of this defence if already selected, null otherwise.
   *
   * @return The arguer for this defence or null.
   */
  public Teacher getArguer() {
    return this.arguer;
  }

  /**
   * Returns the president for this defence if applied, and already selected, null otherwise.
   *
   * @return The president for this defence or null.
   */
  public Teacher getPresident() {
    return this.president;
  }

  /**
   * Sets the classroom for this defence.
   *
   * @param classRoom The classroom for this defence.
   */
  public void setClassRoom(ClassRoom classRoom) {
    this.classRoom = classRoom;
  }

  /**
   * Sets the arguer for this defence.
   *
   * @param arguer The arguer for this defence.
   */
  public void setArguer(Teacher arguer) {
    this.arguer = arguer;
  }

  /**
   * Sets the president for this defence.
   *
   * @param president The president for this defence.
   */
  public void setPresident(Teacher president) {
    this.president = president;
  }

  @Override
  public String toString() {
    return "Defence["
        + "id="
        + id
        + ", grade="
        + grade
        + ", dateTime="
        + dateTime
        + ", duration="
        + duration
        + ", type="
        + type
        + ", classRoom="
        + classRoom
        + ", arguer="
        + arguer
        + ", president="
        + president
        + ']';
  }
}
