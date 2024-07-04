package pt.ul.fc.css.thesismandesktop.services.exceptions;

public class HttpRequestException extends Exception {

  private int statusCode = -1;

  public HttpRequestException(int statusCode) {
    this.statusCode = statusCode;
  }

  public HttpRequestException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  public HttpRequestException(int statusCode, Throwable cause) {
    super(cause);
    this.statusCode = statusCode;
  }

  public HttpRequestException(int statusCode, String message, Throwable cause) {
    super(message, cause);
    this.statusCode = statusCode;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
