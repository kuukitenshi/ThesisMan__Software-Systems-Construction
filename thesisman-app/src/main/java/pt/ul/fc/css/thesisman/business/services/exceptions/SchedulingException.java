package pt.ul.fc.css.thesisman.business.services.exceptions;

public class SchedulingException extends Exception {

  public SchedulingException() {}

  public SchedulingException(String message) {
    super(message);
  }

  public SchedulingException(String message, Throwable cause) {
    super(message, cause);
  }

  public SchedulingException(Throwable cause) {
    super(cause);
  }
}
