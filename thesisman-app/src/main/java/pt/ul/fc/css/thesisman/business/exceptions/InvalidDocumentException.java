package pt.ul.fc.css.thesisman.business.exceptions;

public class InvalidDocumentException extends Exception {

  public InvalidDocumentException() {
  }

  public InvalidDocumentException(String message) {
    super(message);
  }

  public InvalidDocumentException(Throwable cause) {
    super(cause);
  }

  public InvalidDocumentException(String message, Throwable cause) {
    super(message, cause);
  }
}
