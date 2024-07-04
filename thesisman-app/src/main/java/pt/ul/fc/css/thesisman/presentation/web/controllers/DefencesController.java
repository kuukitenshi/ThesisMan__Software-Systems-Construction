package pt.ul.fc.css.thesisman.presentation.web.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pt.ul.fc.css.thesisman.business.services.DefenceService;
import pt.ul.fc.css.thesisman.business.services.dtos.DefenceDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.TeacherDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;
import pt.ul.fc.css.thesisman.presentation.web.beans.DefenceDataCreator;
import pt.ul.fc.css.thesisman.presentation.web.viewdata.DefenceData;

import java.util.List;
import java.util.Optional;

@Controller
public class DefencesController {

  @Autowired private DefenceService defenceService;
  @Autowired private DefenceDataCreator defenceDataCreator;

  @GetMapping("/defences")
  public String getDefences(final Model model, final HttpSession session) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return "redirect:/login";
    }
    if (!(user instanceof TeacherDTO)){
      return "error/401";
    }
    List<DefenceData> defencesData = this.defenceService
            .listParticipatingDefences(user.id())
            .stream()
            .map(this.defenceDataCreator::createDataFromDefence)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("selectedNavOption", "defences");
    model.addAttribute("defencesData", defencesData);
    return "defences";
  }

  @GetMapping("/defences/{id}")
  public String getDefenceDetails(final Model model, final HttpSession session, @PathVariable("id") Long id) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return "redirect:/login";
    }
    if (!(user instanceof TeacherDTO)) {
      return "error/401";
    }
    Optional<DefenceDTO> defenceOpt = this.defenceService.getById(id);
    if (defenceOpt.isEmpty())
      return "error/404";
    DefenceDTO defence = defenceOpt.get();
    Optional<DefenceData> defenceDataOpt = this.defenceDataCreator.createDataFromDefence(defence);
    if (defenceDataOpt.isEmpty())
      return "error/404";
    DefenceData defenceData = defenceDataOpt.get();
    if (!defenceData.thesis().theme().advisor().id().equals(user.id())
            && !defenceData.defence().arguer().id().equals(user.id())
            && (defenceData.defence().president() == null || !defenceData.defence().president().id().equals(user.id()))) {
      return "error/401";
    }
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("selectedNavOption", "defences");
    model.addAttribute("data", defenceData);
    return "defence_details";
  }

  @PostMapping("/defences/{id}")
  public String postDefenceDetails(final HttpSession session, final RedirectAttributes redirectAttributes, @PathVariable("id") Long defenceId, @RequestParam("grade") Float grade) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return "redirect:/login";
    }
    Optional<DefenceDTO> defenceOpt = this.defenceService.getById(defenceId);
    if (defenceOpt.isEmpty())
      return "error/404";
    DefenceDTO defence = defenceOpt.get();
    Optional<DefenceData> defenceDataOpt = this.defenceDataCreator.createDataFromDefence(defence);
    if (defenceDataOpt.isEmpty()) {
      return "error/404";
    }
    DefenceData defenceData = defenceDataOpt.get();
    if (defenceData.submission().type().equals("FINAL")) {
      if (defenceData.defence().president() != null && defenceData.defence().president().id().equals(user.id())) {
        try {
          this.defenceService.gradeFinalDefence(defenceId, grade);
        } catch (NotFoundException | InvalidFieldException e) {
          redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
      } else {
        return "error/401";
      }
    } else if (defenceData.submission().type().equals("PROPOSAL")) {
      if (defenceData.thesis().theme().advisor().id().equals(user.id())) {
        try {
          this.defenceService.gradeProposalDefence(defenceId, grade);
        } catch (NotFoundException | InvalidFieldException e) {
          redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
      } else {
        return "error/401";
      }
    }
    return "redirect:/defences/" + defenceId;
  }
}
