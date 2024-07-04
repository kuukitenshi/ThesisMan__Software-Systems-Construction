package pt.ul.fc.css.thesisman.business.exceptions;

public class MaxApplicationsExceedException extends Exception {

  public MaxApplicationsExceedException() {
  }

  public MaxApplicationsExceedException(String message) {
    super(message);
  }

  public MaxApplicationsExceedException(Throwable cause) {
    super(cause);
  }

  public MaxApplicationsExceedException(String message, Throwable cause) {
    super(message, cause);
  }
}
