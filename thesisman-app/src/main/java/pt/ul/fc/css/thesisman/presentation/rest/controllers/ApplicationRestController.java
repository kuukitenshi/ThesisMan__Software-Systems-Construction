package pt.ul.fc.css.thesisman.presentation.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.ul.fc.css.thesisman.business.services.ApplicationService;
import pt.ul.fc.css.thesisman.business.services.dtos.ApplicationDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.StudentDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.IncompatiblityException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;
import pt.ul.fc.css.thesisman.business.services.exceptions.StudentMaxApplicationException;
import pt.ul.fc.css.thesisman.presentation.rest.objects.ApplicationCreate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ApplicationRestController {

    @Autowired private ApplicationService applicationService;
    @Autowired private HttpSession httpSession;

  @Operation(summary = "Get all the applications of a student", description = "This operation returns all the applications of a student.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found the applications", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = ApplicationDTO.class)) }),
          @ApiResponse(responseCode = "401", description = "Invalid student authenticated id", content = @Content),
          @ApiResponse(responseCode = "404", description = "Not found applications for this student", content = @Content) })
    @GetMapping("/application")
    public ResponseEntity<List<ApplicationDTO>> studentApplicationsList(@Parameter(description = "student id for search applications") @RequestParam("studentId") Long studentId) {
      IUserDTO user = (IUserDTO) httpSession.getAttribute("user");
      if (!(user instanceof StudentDTO) || !user.id().equals(studentId)) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
      try{
        List<ApplicationDTO> applicationsListByStudent = applicationService.studentApplicationsList(studentId);
        return ResponseEntity.ok().body(applicationsListByStudent);
      } catch (NotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @Operation(summary = "Creates an application", description = "This operation creates an application for a student.")
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The application was created successfully", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = ApplicationDTO.class)) }),
          @ApiResponse(responseCode = "401", description = "Invalid student authenticated id", content = @Content),
          @ApiResponse(responseCode = "404", description = "Cannot create the application for this student", content = @Content) })
    @PostMapping("/application")
    public ResponseEntity<ApplicationDTO> addApplication(@Parameter(description = "The body with the values for the attributes of the application", schema = @Schema(implementation = ApplicationCreate.class)) @RequestBody ApplicationCreate applicationCreate) {
      Long themeId = applicationCreate.themeId();
      Long studentId = applicationCreate.studentId();
      IUserDTO user = (IUserDTO) httpSession.getAttribute("user");
      if (!(user instanceof StudentDTO) || !user.id().equals(studentId)) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
      try{
        ApplicationDTO application = applicationService.addApplication(themeId, studentId);
        return ResponseEntity.ok().body(application);
      } catch (NotFoundException | StudentMaxApplicationException | AlreadyExistsException | IncompatiblityException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }


  @Operation(summary = "Cancels an application", description = "This operation cancels an application.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The application was canceled successfully", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = Boolean.class)) }),
          @ApiResponse(responseCode = "401", description = "Invalid student authenticated id", content = @Content),
          @ApiResponse(responseCode = "404", description = "Not found application to cancel", content = @Content) })
  @DeleteMapping("/application/{applicationId}")
  public ResponseEntity<Boolean> cancelApplication(@Parameter(description = "The id of the application to be canceled") @PathVariable("applicationId") Long applicationId) {
    IUserDTO user = (IUserDTO) httpSession.getAttribute("user");
    if (!(user instanceof StudentDTO)) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Optional<ApplicationDTO> applicationOpt = this.applicationService.getById(applicationId);
    if (applicationOpt.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    ApplicationDTO application = applicationOpt.get();
    if (!user.id().equals(application.student().id())) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    try{
      applicationService.cancelApplication(applicationId);
      return ResponseEntity.ok().body(true);
    }catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
