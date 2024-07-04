package pt.ul.fc.css.thesisman.business.exceptions;

public class StudentNotFoundException extends Exception {

  public StudentNotFoundException() {
  }

  public StudentNotFoundException(String message) {
    super(message);
  }

  public StudentNotFoundException(Throwable cause) {
    super(cause);
  }

  public StudentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
