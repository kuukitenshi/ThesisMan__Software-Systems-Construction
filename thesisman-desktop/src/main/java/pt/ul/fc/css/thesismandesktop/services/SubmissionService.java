package pt.ul.fc.css.thesismandesktop.services;

import pt.ul.fc.css.thesismandesktop.model.objects.Submission;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SubmissionService {

  private static final String API_URL = "http://localhost:8080/api";

  private final HttpService httpService = HttpService.getInstance();

  public Submission submitProposeDocument(Long thesisId, File doc) throws HttpRequestException {
    String endpoint = API_URL + "/submission/propose?thesisId=" + thesisId + "&file=" + doc.getName();
    return this.httpService.uploadFile(endpoint, doc, Submission.class);
  }

  public Submission submitFinalDocument(Long thesisId, File doc) throws HttpRequestException {
    String endpoint = API_URL + "/submission/final?thesisId=" + thesisId + "&file=" + doc.getName();
    return this.httpService.uploadFile(endpoint, doc, Submission.class);
  }

  public Submission loadFinal(Long thesisId) throws HttpRequestException {
    String endpoint = API_URL + "/submission/final?thesisId=" + thesisId;
    return this.httpService.get(endpoint, Submission.class);
  }

  public List<Submission> loadProposals(Long thesisId) throws HttpRequestException {
    String endpoint = API_URL + "/submission/proposals?thesisId=" + thesisId;
    Submission[] submissions = this.httpService.get(endpoint, Submission[].class);
    return Arrays.asList(submissions);
  }
}
