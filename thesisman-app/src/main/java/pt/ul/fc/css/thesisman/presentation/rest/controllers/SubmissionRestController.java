package pt.ul.fc.css.thesisman.presentation.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.exceptions.SubmissionNotFoundException;
import pt.ul.fc.css.thesisman.business.exceptions.ThesisNotFoundException;
import pt.ul.fc.css.thesisman.business.services.SubmissionService;
import pt.ul.fc.css.thesisman.business.services.dtos.SubmissionDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;
import pt.ul.fc.css.thesisman.presentation.rest.objects.ApplicationCreate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api")
public class SubmissionRestController {

  @Autowired private SubmissionService submissionService;

  @Operation(summary = "Creates a proposal submission of a document for the specified thesis", description = "This operation creates a proposal submission of a document for a given thesis.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The proposal submission was created successfully", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = SubmissionDTO.class)) }),
          @ApiResponse(responseCode = "404", description = "Cannot create the proposal submission", content = @Content) })
  @PostMapping("/submission/propose")
  public ResponseEntity<SubmissionDTO> submitProposalDocument(@Parameter(description = "the id of the thesis to which the proposal submission belongs") @RequestParam("thesisId") Long thesisId, @Parameter(description = "the file to be submitted", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestParam("file") MultipartFile file) {
    try {
      SubmissionDTO submission = submissionService.submitProposalDocument(thesisId, file.getBytes(), file.getOriginalFilename());
      return ResponseEntity.ok().body(submission);
    } catch (NotFoundException | InvalidFieldException | IOException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Creates a final submission of a document for the specified thesis", description = "This operation creates a final submission of a document for a given thesis.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The final submission was created successfully", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = SubmissionDTO.class)) }),
          @ApiResponse(responseCode = "404", description = "Cannot create the final submission", content = @Content) })
  @PostMapping("/submission/final")
  public ResponseEntity<SubmissionDTO> submitFinalDocument(@Parameter(description = "the id of the thesis to which the final submission belongs") @RequestParam("thesisId") Long thesisId, @Parameter(description = "the file to be submitted", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestParam("file") MultipartFile file) {
    try {
      SubmissionDTO submission = submissionService.submitFinalDocument(thesisId, file.getBytes(), file.getOriginalFilename());
      return ResponseEntity.ok().body(submission);
    } catch (NotFoundException | InvalidFieldException | IOException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Loads the final submission of a thesis", description = "This operation loads the final submission of a given thesis.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found the final submission ", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = SubmissionDTO.class)) }),
          @ApiResponse(responseCode = "404", description = "Not found the final submission", content = @Content) })
  @GetMapping("/submission/final")
  public ResponseEntity<SubmissionDTO> loadFinal(@Parameter(description = "the id of the thesis to which the final submission belongs") @RequestParam("thesisId") Long thesisId) {
    try {
      return ResponseEntity.ok().body(submissionService.loadFinal(thesisId));
    } catch (SubmissionNotFoundException | ThesisNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(summary = "Loads all the proposal submission of a thesis", description = "This operation loads the list of proposal submission of a given thesis.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found proposal submissions", content = { @Content(mediaType ="application/json", array = @ArraySchema(schema = @Schema(implementation = SubmissionDTO.class))) }),
          @ApiResponse(responseCode = "404", description = "Not found any proposal submission", content = @Content) })
  @GetMapping("/submission/proposals")
  public ResponseEntity<List<SubmissionDTO>> loadProposals(@Parameter(description = "the id of the thesis to which the proposal submissions belongs") @RequestParam("thesisId") Long thesisId) {
    try {
      return ResponseEntity.ok().body(submissionService.loadProposals(thesisId));
    } catch (ThesisNotFoundException | SubmissionNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
