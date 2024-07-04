package pt.ul.fc.css.thesisman.presentation.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import pt.ul.fc.css.thesisman.business.services.ThemeService;
import pt.ul.fc.css.thesisman.business.services.dtos.*;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("api")
class ThemeRestController {

  @Autowired private ThemeService themeService;
  @Autowired private HttpSession httpSession;

  @Operation(summary = "Get the themes by the specified year", description = "This operation returns the themes available in the specified year.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "List of themes founded for the given year", content = { @Content(mediaType ="application/json", array = @ArraySchema(schema = @Schema(anyOf ={ProjectDTO.class, DissertationDTO.class} ))) }),
          @ApiResponse(responseCode = "401", description = "Invalid student authenticated id", content = @Content),
          @ApiResponse(responseCode = "404", description = "Not found themes for this year", content = @Content) })
  @GetMapping("/themes")
  public ResponseEntity<List<IThemeDTO>> listThemesByYear(@Parameter(description = "year for search themes") @RequestParam("year") String year) {
    IUserDTO user = (IUserDTO) httpSession.getAttribute("user");
    if (user instanceof StudentDTO student) {
      try {
        List<IThemeDTO> themes = themeService.getByYear(year, student.mastersDegree().id());
        return ResponseEntity.ok().body(themes);
      } catch (InvalidFieldException | NotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }
}
