package pt.ul.fc.css.thesisman.presentation.web.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String getIndex(final HttpSession session) {
    if (session.getAttribute("user") == null) {
      return "redirect:/login";
    }
    return "redirect:/dashboard";
  }
}
