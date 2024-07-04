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
import pt.ul.fc.css.thesisman.business.services.*;
import pt.ul.fc.css.thesisman.business.services.dtos.*;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;
import pt.ul.fc.css.thesisman.business.services.exceptions.SchedulingException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ThesisController {

  private static final String LOGIN_REDIRECT = "redirect:/login";

  @Autowired private ThesisService thesisService;
  @Autowired private SubmissionService submissionService;
  @Autowired private TeacherService teacherService;
  @Autowired private ClassRoomService classRoomService;
  @Autowired private DefenceService defenceService;

  @GetMapping("/thesis")
  public String getThesis(final Model model, final HttpSession session) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    if (!(user instanceof TeacherDTO)) {
      return "error/401";
    }
    model.addAttribute("user", user);
    model.addAttribute("selectedNavOption", "thesis");
    model.addAttribute("thesis", this.thesisService.getThesisFromTeacher(user.id()));
    return "thesis";
  }

  @GetMapping("/thesis/{id}")
  public String getThesisDetail(final Model model, final HttpSession session, @PathVariable("id") long id) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    Optional<ThesisDTO> thesisOpt = this.thesisService.getById(id);
    if (thesisOpt.isEmpty()) {
      return "error/404";
    }
    ThesisDTO thesis = thesisOpt.get();
    if (!user.id().equals(thesis.theme().advisor().id())) {
      return "error/404";
    }
    model.addAttribute("user", user);
    model.addAttribute("selectedNavOption", "thesis");
    model.addAttribute("thesis", thesis);
    return "thesis_detail";
  }

  @GetMapping("/thesis/{thesisId}/scheduleDefence/{submissionId}")
  public String getScheduleDefence(final Model model, final HttpSession session, @PathVariable("thesisId") Long thesisId, @PathVariable("submissionId") Long submissionId) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    Optional<ThesisDTO> thesisOpt = this.thesisService.getById(thesisId);
    if (thesisOpt.isEmpty())
      return "error/404";
    ThesisDTO thesis = thesisOpt.get();
    if (!user.id().equals(thesis.theme().advisor().id())) {
      return "error/401";
    }
    Optional<SubmissionDTO> submissionOpt = this.submissionService.getById(submissionId);
    if (submissionOpt.isEmpty())
      return "error/404";
    SubmissionDTO submission = submissionOpt.get();
    List<TeacherDTO> teachers = this.teacherService.getTeachers().stream().filter(t -> !t.id().equals(user.id())).toList();
    List<ClassRoomDTO> classrooms = this.classRoomService.getClassRooms();
    model.addAttribute("user", user);
    model.addAttribute("selectedNavOption", "thesis");
    model.addAttribute("thesis", thesis);
    model.addAttribute("submission", submission);
    model.addAttribute("arguers", teachers);
    model.addAttribute("classrooms", classrooms);
    return "schedule_defence";
  }

  @PostMapping("/thesis/{thesisId}/scheduleDefence/{submissionId}")
  public String postScheduleDefence(final HttpSession session,
                                    final RedirectAttributes redirectAttributes,
                                    @PathVariable("thesisId") Long thesisId,
                                    @PathVariable("submissionId") Long submissionId,
                                    @RequestParam("date") LocalDateTime date,
                                    @RequestParam("arguer") Long arguerId,
                                    @RequestParam(value = "classroom", required = false) Long classRoomId,
                                    @RequestParam(value = "president", required = false) Long presidentId,
                                    @RequestParam("type") String type
                                    ) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    Optional<ThesisDTO> thesisOpt = this.thesisService.getById(thesisId);
    if (thesisOpt.isEmpty()) {
      return "error/404";
    }
    ThesisDTO thesis = thesisOpt.get();
    if (!user.id().equals(thesis.theme().advisor().id())) {
      return "error/401";
    }
    try {
      this.defenceService.scheduleDefence(submissionId, type, date, arguerId, classRoomId, presidentId);
    } catch (NotFoundException | AlreadyExistsException | InvalidFieldException | SchedulingException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "Failed to schedule: " + e.getMessage());
      return "redirect:/thesis/" + thesisId + "/scheduleDefence/" + submissionId;
    }
    return "redirect:/thesis/" + thesisId;
  }

}
