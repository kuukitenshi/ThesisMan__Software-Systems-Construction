package pt.ul.fc.css.thesismandesktop.services;

import pt.ul.fc.css.thesismandesktop.model.objects.Theme;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.util.Arrays;
import java.util.List;

public class ThemeService {

  private static final String API_URL = "http://localhost:8080/api";

  private final HttpService httpService = HttpService.getInstance();

  public List<Theme> listThemeByYear(String year) throws HttpRequestException {
    String endpoint = API_URL + "/themes?year=" + year;
    Theme[] themes = this.httpService.get(endpoint, Theme[].class);
    return Arrays.asList(themes);
  }
}
