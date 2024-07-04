package pt.ul.fc.css.thesismandesktop.services;

import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;
import pt.ul.fc.css.thesismandesktop.services.objects.ApplicationCreate;

import java.util.Arrays;
import java.util.List;

public class ApplicationService {

  private static final String API_URL = "http://localhost:8080/api";

  private final HttpService httpService = HttpService.getInstance();

  public List<Application> studentApplicationsList(long studentId) throws HttpRequestException {
    String endpoint = API_URL + "/application?studentId=" + studentId;
    Application[] applications = this.httpService.get(endpoint, Application[].class);
    return Arrays.asList(applications);
  }

  public Application addApplication(long studentId, long themeId) throws HttpRequestException {
    ApplicationCreate object = new ApplicationCreate(studentId, themeId);
    String endpoint = API_URL + "/application";
    return this.httpService.post(endpoint, object, Application.class);
  }

  public boolean cancelApplication(long applicationId) throws HttpRequestException {
    String endpoint = API_URL + "/application/" + applicationId;
    return this.httpService.delete(endpoint, Boolean.class);
  }
}
