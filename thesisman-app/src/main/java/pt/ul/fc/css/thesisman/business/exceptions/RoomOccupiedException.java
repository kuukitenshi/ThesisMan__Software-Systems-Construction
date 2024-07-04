package pt.ul.fc.css.thesisman.business.exceptions;

public class RoomOccupiedException extends Exception {

  public RoomOccupiedException() {
  }

  public RoomOccupiedException(String message) {
    super(message);
  }

  public RoomOccupiedException(Throwable cause) {
    super(cause);
  }

  public RoomOccupiedException(String message, Throwable cause) {
    super(message, cause);
  }
}
