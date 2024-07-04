package pt.ul.fc.css.thesisman.business.services.exceptions;

public class StudentMaxApplicationException extends Exception {

  public StudentMaxApplicationException() {}

  public StudentMaxApplicationException(String message) {
    super(message);
  }

  public StudentMaxApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public StudentMaxApplicationException(Throwable cause) {
    super(cause);
  }
}
