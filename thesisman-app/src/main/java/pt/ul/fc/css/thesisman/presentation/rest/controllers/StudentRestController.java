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
import pt.ul.fc.css.thesisman.business.services.AuthenticationService;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.StudentDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

@RestController
@RequestMapping("api")
public class StudentRestController {

  @Autowired private AuthenticationService authenticationService;
  @Autowired private HttpSession httpSession;

  @Operation(summary = "Authenticates a student by username and password ", description = "This operation authenticates a student by username and password and returns the student authenticated if it was successful.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The student was successfully authenticated", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = StudentDTO.class)) }),
          @ApiResponse(responseCode = "401", description = "Invalid student id", content = @Content),
          @ApiResponse(responseCode = "404", description = "Cannot authenticate the student", content = @Content) })
  @GetMapping("/student")
  public ResponseEntity<IUserDTO> authenticateStudent(@Parameter(description = "student's username for authentication") @RequestParam("username") String username, @Parameter(description = "student's password for authentication") @RequestParam("password") String password) {
    try{
      IUserDTO user = authenticationService.authenticate(username, password);
      if (!(user instanceof StudentDTO)) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
      this.httpSession.setAttribute("user", user);
      return ResponseEntity.ok().body(user);
    } catch (InvalidFieldException | NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
