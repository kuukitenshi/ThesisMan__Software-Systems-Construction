package pt.ul.fc.css.thesisman.business.exceptions;

public class ClassRoomNotFoundException extends Exception {

  public ClassRoomNotFoundException() {
  }

  public ClassRoomNotFoundException(String message) {
    super(message);
  }

  public ClassRoomNotFoundException(Throwable cause) {
    super(cause);
  }

  public ClassRoomNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
