package pt.ul.fc.css.thesisman.presentation.web.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pt.ul.fc.css.thesisman.business.services.AuthenticationService;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.StudentDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.WorkerDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

@Controller
public class AuthenticationController {

  private static final String LOGGED_IN_REDIRECT = "redirect:/dashboard";

  @Autowired
  private AuthenticationService authenticationService;

  @GetMapping("/login")
  public String getLogin(final HttpSession session) {
    if (session.getAttribute("user") != null) {
      return LOGGED_IN_REDIRECT;
    }
    return "login";
  }

  @PostMapping("/login")
  public String postLogin(
          @RequestParam("username") String username,
          @RequestParam("password") String password,
          final HttpSession session,
          final RedirectAttributes redirectAttributes) {
    try {
      IUserDTO user = this.authenticationService.authenticate(username, password);
      if (user instanceof StudentDTO) {
        return "error/401";
      }
      session.setAttribute("user", user);
      return LOGGED_IN_REDIRECT;
    } catch (NotFoundException | InvalidFieldException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Login failed: " + e.getMessage());
      return "redirect:/login";
    }
  }

  @GetMapping("/register")
  public String getRegister() {
    return "register";
  }

  @PostMapping("/register")
  public String postRegister(
          @RequestParam("username") String username,
          @RequestParam("password") String password,
          @RequestParam("name") String name,
          @RequestParam("company") String company,
          final HttpSession session,
          final RedirectAttributes redirectAttributes) {
    try {
      WorkerDTO worker = this.authenticationService.registerWorker(username, password, name, company);
      session.setAttribute("user", worker);
      return LOGGED_IN_REDIRECT;
    } catch (InvalidFieldException | AlreadyExistsException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Register failed: " + e.getMessage());
      return "redirect:/register";
    }
  }

  @GetMapping("/logout")
  public String getLogout(final HttpSession session) {
    session.invalidate();
    return "logout";
  }
}
