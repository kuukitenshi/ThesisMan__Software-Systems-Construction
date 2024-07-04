package pt.ul.fc.css.thesismandesktop.services;

import pt.ul.fc.css.thesismandesktop.model.objects.Student;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

public class AuthenticationService {

  private static final String API_URL = "http://localhost:8080/api";

  private final HttpService httpService = HttpService.getInstance();

  public Student authenticateStudent(String username, String password) throws HttpRequestException {
    String endpoint = API_URL + "/student?username=" + username + "&password=" + password;
    return this.httpService.get(endpoint, Student.class);
  }

  public void logout() throws HttpRequestException {
    String endpoint = API_URL + "/logout";
    this.httpService.get(endpoint, Boolean.class);
  }
}
