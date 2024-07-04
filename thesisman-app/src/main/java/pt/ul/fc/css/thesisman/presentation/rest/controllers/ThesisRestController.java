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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.ul.fc.css.thesisman.business.services.ThesisService;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.StudentDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.ThesisDTO;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class ThesisRestController {

  @Autowired private ThesisService thesisService;
  @Autowired private HttpSession httpSession;

  @Operation(summary = "Get the thesis of the student specified by the id", description = "This operation returns the thesis of the student specified by the id.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Found the thesis of the student", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = ThesisDTO.class)) }),
          @ApiResponse(responseCode = "401", description = "Invalid student authenticated id", content = @Content),
          @ApiResponse(responseCode = "404", description = "Not found thesis for this student", content = @Content) })
  @GetMapping("/thesis")
  public ResponseEntity<ThesisDTO> studentThesisList(@Parameter(description = "student id for search thesis") @RequestParam("studentId") Long studentId) {
    IUserDTO user = (IUserDTO) this.httpSession.getAttribute("user");
    if (!(user instanceof StudentDTO) || !user.id().equals(studentId)) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Optional<ThesisDTO> thesisFromStudent = thesisService.getThesisFromStudent(studentId);
    if(thesisFromStudent.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok().body(thesisFromStudent.get());
  }
}
