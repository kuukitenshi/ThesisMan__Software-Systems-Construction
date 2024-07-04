package pt.ul.fc.css.thesismandesktop.services;

import pt.ul.fc.css.thesismandesktop.model.objects.Thesis;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

public class ThesisService {

  private static final String API_URL = "http://localhost:8080/api";

  private final HttpService httpService = HttpService.getInstance();

  public Thesis getThesisFromStudent(long studentId) throws HttpRequestException {
    String endpoint = API_URL + "/thesis?studentId=" + studentId;
    return this.httpService.get(endpoint, Thesis.class);
  }
}
