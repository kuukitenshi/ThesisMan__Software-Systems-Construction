package pt.ul.fc.css.thesisman.business.entities;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import pt.ul.fc.css.thesisman.business.enums.SubmissionType;

import java.time.LocalDateTime;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This class is used to represent a Submission.
 */
@Entity
public class Submission {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NonNull
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime date = LocalDateTime.now();

  @NonNull @Lob private byte[] bytes;

  @NonNull
  @Enumerated(EnumType.STRING)
  private SubmissionType type = SubmissionType.PROPOSAL;

  @OneToOne(cascade = CascadeType.ALL)
  private Defence defence;

  @NonNull
  private String filename;

  /**
   * Protected constructor required by JPA, initializes all {@code @NonNull} annotated attributes
   * with default values.
   */
  protected Submission() {
    this.bytes = new byte[1024];
    this.filename = "";
  }

  /**
   * Creates a new instance of a Submission with the specified file and submission type.
   *
   * @param file The content of the file with the thesis proposal submission.
   * @param type The type of the submission.
   */
  public Submission(@NonNull SubmissionType type, @NonNull byte[] file, String filename) {
    this.bytes = file;
    this.type = type;
    this.filename = filename;
  }

  /**
   * Returns the id of this submission.
   *
   * @return The id generated by the database for this submission.
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the content of the file with the thesis proposal submission.
   *
   * @return The content of the file with the thesis proposal submission.
   */
  public @NonNull byte[] getBytes() {
    return this.bytes;
  }

  /**
   * Sets the content of the file with the thesis proposal for this submission.
   *
   * @param file The content of the file with the thesis proposal.
   */
  public void setBytes(@NonNull byte[] file) {
    this.bytes = file;
  }

  /**
   * Returns the timestamp of the submission including hours and date.
   *
   * @return The timestamp of the submission including hours and date.
   */
  public @NonNull LocalDateTime getDate() {
    return this.date;
  }

  /**
   * Sets the timestamp of the submission including hours and date for this submission.
   *
   * @param date The timestamp of the submission including hours and date.
   */
  public void setDate(@NonNull LocalDateTime date) {
    this.date = date;
  }

  /**
   * Returns the type of this submission.
   *
   * @return The submission type of this submission.
   */
  public @NonNull SubmissionType getType() {
    return this.type;
  }

  /**
   * Returns the defense of this submission.
   *
   * @return The defense of this submission.
   */
  public Defence getDefence() {
    return defence;
  }

  /**
   * Sets the defence for this submission.
   *
   * @param defence The defence for this submission.
   */
  public void setDefence(Defence defence) {
    this.defence = defence;
  }

  @NonNull
  public String getFilename() {
    return filename;
  }

  @Override
  public String toString() {
    return "Submission["
        + "id="
        + id
        + ", date="
        + date
        + ", type="
        + type
        + ", defence="
        + defence
        + "]";
  }
}
