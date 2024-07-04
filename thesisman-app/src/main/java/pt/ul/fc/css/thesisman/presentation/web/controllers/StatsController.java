package pt.ul.fc.css.thesisman.presentation.web.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pt.ul.fc.css.thesisman.business.services.StatisticsService;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.StatisticsDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.TeacherDTO;

@Controller
public class StatsController {

  @Autowired private StatisticsService statisticsService;

  @GetMapping("/stats")
  public String getStatistics(final Model model, final HttpSession session) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return "redirect:/login";
    }
    if (!(user instanceof TeacherDTO)) {
      return "error/401";
    }
    StatisticsDTO stats = this.statisticsService.getStatistics();
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("selectedNavOption", "stats");
    model.addAttribute("stats", stats);
    return "statistics";
  }
}
