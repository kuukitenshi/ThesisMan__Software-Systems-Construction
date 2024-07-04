package pt.ul.fc.css.thesisman.presentation.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LogoutRestController {

  @Autowired private HttpSession httpSession;

  @Operation(summary = "Make a student logout from the application", description = "This operation invalidates the session of the student, making it logout from the application.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "The student was logout successfully", content = { @Content(mediaType ="application/json", schema = @Schema(implementation = Boolean.class)) }),})
  @GetMapping("/logout")
  public ResponseEntity<Boolean> logout() {
    httpSession.invalidate();
    return ResponseEntity.ok(true);
  }
}
