package pt.ul.fc.css.thesisman.business.exceptions;

public class MastersDegreeNotFoundException extends Exception {

  public MastersDegreeNotFoundException() {
  }

  public MastersDegreeNotFoundException(String message) {
    super(message);
  }

  public MastersDegreeNotFoundException(Throwable cause) {
    super(cause);
  }

  public MastersDegreeNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
