package pt.ul.fc.css.thesisman.presentation.web.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;

@Controller
public class DashboardController {

  @GetMapping("/dashboard")
  public String getDashboard(final Model model, final HttpSession session) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return "redirect:/login";
    }
    model.addAttribute("user", user);
    model.addAttribute("selectedNavOption", "dashboard");
    return "dashboard";
  }
}
