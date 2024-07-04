package pt.ul.fc.css.thesisman.business.exceptions;

public class TeacherNotFoundException extends Exception {

  public TeacherNotFoundException() {
  }

  public TeacherNotFoundException(String message) {
    super(message);
  }

  public TeacherNotFoundException(Throwable cause) {
    super(cause);
  }

  public TeacherNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
