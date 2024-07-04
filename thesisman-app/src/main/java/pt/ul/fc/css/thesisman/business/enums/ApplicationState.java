package pt.ul.fc.css.thesisman.business.enums;

/**
 * @author 58180 Rodrigo Correia
 * @author 58188 Laura Cunha
 * @author 58640 Guilherme Wind
 *     <p>This enum is used to represent the state of an Application. The state can be: - ACCEPTED
 *     if the application has been accepted and the theme given to the student. - REJECTED if the
 *     student didn't win the theme. - SUBMITTED if the application has been submitted and still
 *     being processed.
 */
public enum ApplicationState {
  ACCEPTED,
  REJECTED,
  SUBMITTED
}
