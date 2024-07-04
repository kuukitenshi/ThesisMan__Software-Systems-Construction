package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.SubmissionCatalog;
import pt.ul.fc.css.thesisman.business.catalogs.ThesisCatalog;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.entities.Thesis;
import pt.ul.fc.css.thesisman.business.enums.SubmissionType;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.handlers.SubmitFinalDocumentHandler;
import pt.ul.fc.css.thesisman.business.handlers.SubmitProposalDocumentHandler;
import pt.ul.fc.css.thesisman.business.services.dtos.SubmissionDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

  @Autowired private SubmitProposalDocumentHandler submitProposalDocumentHandler;
  @Autowired private SubmitFinalDocumentHandler submitFinalDocumentHandler;
  @Autowired private SubmissionCatalog submissionCatalog;
  @Autowired private ThesisCatalog thesisCatalog;

  public Optional<SubmissionDTO> getById(Long submissionId) {
    return this.submissionCatalog.getById(submissionId).map(SubmissionDTO::fromSubmission);
  }

  public Optional<SubmissionDTO> getByDefenceId(Long defenceId) {
    return this.submissionCatalog.getByDefenceId(defenceId).map(SubmissionDTO::fromSubmission);
  }

  public SubmissionDTO submitProposalDocument(Long thesisId, byte[] bytes, String filename) throws NotFoundException, InvalidFieldException {
    try {
      return SubmissionDTO.fromSubmission(submitProposalDocumentHandler.submitProposalDocument(thesisId, bytes, filename));
    } catch (ThesisNotFoundException e) {
      throw new NotFoundException("Thesis with id " + thesisId + " was not found!", e);
    } catch (InvalidDocumentException e) {
      throw new InvalidFieldException("Invalid document submitted!", e);
    } catch (InvalidNameException e) {
      throw new InvalidFieldException("Invalid document name!", e);
    }
  }

  public SubmissionDTO submitFinalDocument(Long thesisId, byte[] bytes, String filename) throws NotFoundException, InvalidFieldException {
    try {
      return SubmissionDTO.fromSubmission(submitFinalDocumentHandler.submitFinalDocument(thesisId, bytes, filename));
    } catch (ThesisNotFoundException e) {
      throw new NotFoundException("Thesis with id " + thesisId + " was not found!", e);
    } catch (InvalidDocumentException e) {
      throw new InvalidFieldException("Invalid document submitted!", e);
    } catch (AlreadyHasFinalSubmissionException e) {
      throw new InvalidFieldException("Student already has submitted the final document!", e);
    } catch (InvalidNameException e) {
      throw new InvalidFieldException("Invalid document name!", e);
    }
  }

  public SubmissionDTO loadFinal(Long thesisId) throws ThesisNotFoundException, SubmissionNotFoundException {
    Optional<Thesis> thesis = thesisCatalog.getBySubmissionId(thesisId);
    if (thesis.isEmpty())
      throw new ThesisNotFoundException("Thesis with id " + thesisId + " was not found!");
    Submission submission = thesis.get().getSubmissions().stream().filter(s -> s.getType().equals(SubmissionType.FINAL)).findFirst().orElse(null);
    if (submission == null)
      throw new SubmissionNotFoundException("Thesis with id " + thesisId + " has no final submission!");
    return SubmissionDTO.fromSubmission(submission);
  }

  public List<SubmissionDTO> loadProposals(Long thesisId) throws ThesisNotFoundException, SubmissionNotFoundException {
    Optional<Thesis> thesis = thesisCatalog.getBySubmissionId(thesisId);
    if (thesis.isEmpty())
      throw new ThesisNotFoundException("Thesis with id " + thesisId + " was not found!");
    List<Submission> submissionList = thesis.get().getSubmissions().stream().filter(s -> s.getType().equals(SubmissionType.PROPOSAL)).toList();
    if (submissionList.isEmpty())
      throw new SubmissionNotFoundException("Thesis with id " + thesisId + " has no final submission!");
    return submissionList.stream().map(SubmissionDTO::fromSubmission).toList();
  }

}
