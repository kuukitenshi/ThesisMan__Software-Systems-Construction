package pt.ul.fc.css.thesisman.business.exceptions;

public class DefenceAlreadyScheduledException extends Exception {

  public DefenceAlreadyScheduledException() {
  }

  public DefenceAlreadyScheduledException(String message) {
    super(message);
  }

  public DefenceAlreadyScheduledException(Throwable cause) {
    super(cause);
  }

  public DefenceAlreadyScheduledException(String message, Throwable cause) {
    super(message, cause);
  }
}
