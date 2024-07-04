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
import pt.ul.fc.css.thesisman.business.services.MastersDegreeService;
import pt.ul.fc.css.thesisman.business.services.StudentService;
import pt.ul.fc.css.thesisman.business.services.TeacherService;
import pt.ul.fc.css.thesisman.business.services.ThemeService;
import pt.ul.fc.css.thesisman.business.services.dtos.*;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Controller
public class ThemeController {

  private static final String LOGIN_REDIRECT = "redirect:/login";

  @Autowired private ThemeService themeService;
  @Autowired private MastersDegreeService mastersDegreeService;
  @Autowired private TeacherService teacherService;
  @Autowired private StudentService studentService;

  @GetMapping("/themes")
  public String getThemes(final Model model, final HttpSession session) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    model.addAttribute("user", user);
    model.addAttribute("selectedNavOption", "themes");
    model.addAttribute("themes", this.themeService.getThemes());
    return "themes";
  }

  @GetMapping("/themes/submit")
  public String getSubmitThemes(final Model model, final HttpSession session) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    if (user instanceof WorkerDTO) {
      model.addAttribute("isWorker", true);
      model.addAttribute("advisors", this.teacherService.getTeachers());
    }
    model.addAttribute("masters", this.mastersDegreeService.getMastersDegrees());
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("selectedNavOption", "themes");
    return "submit_theme";
  }

  @PostMapping("/themes/submit")
  public String postSubmitTheme(final HttpSession session, final RedirectAttributes redirectAttributes, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("renumeration") float renumeration, @RequestParam("masters") List<Long> masters, @RequestParam(value = "advisor", required = false) Long advisorId) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    if (user instanceof TeacherDTO teacher) {
      try {
        this.themeService.submitDissertation(title, description, teacher.id(), renumeration, masters);
      } catch (NotFoundException | InvalidFieldException e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Submission failed: " + e.getMessage());
        return "redirect:/themes/submit";
      }
    } else if (user instanceof WorkerDTO worker) {
      try {
        this.themeService.submitProject(title, description, advisorId, renumeration, masters, worker.id());
      } catch (NotFoundException | InvalidFieldException e) {
        redirectAttributes.addFlashAttribute("errorMessage", "Submission failed: " + e.getMessage());
        return "redirect:/themes/submit";
      }
    }
    return "redirect:/themes";
  }

  @GetMapping("/themes/{themeId}")
  public String getThemeDetails(final Model model, final HttpSession session, @PathVariable("themeId") Long themeId) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    Optional<IThemeDTO> themeOpt = this.themeService.getById(themeId);
    if (themeOpt.isEmpty()) return "error/404";
    IThemeDTO theme = themeOpt.get();
    if (user instanceof TeacherDTO teacher) {
      List<MastersDegreeDTO> teacherMasters = theme.compatibleMasters().stream().filter(m -> m.admin().equals(teacher)).toList();
      if (!teacherMasters.isEmpty()) {
        model.addAttribute("isAdmin", true);
        List<MastersDegreeDTO> mastersIntersect = teacherMasters.stream().filter(m -> theme.compatibleMasters().contains(m)).toList();
        model.addAttribute("students", this.studentService.getAllStudentsWithoutThesis().stream().filter(s -> mastersIntersect.contains(s.mastersDegree())).toList());
      }
    }
    model.addAttribute("user", session.getAttribute("user"));
    model.addAttribute("selectedNavOption", "themes");
    model.addAttribute("theme", theme);
    return "theme_details";
  }

  @PostMapping("/themes/{themeId}")
  public String postThemeDetails(final HttpSession session, @PathVariable("themeId") Long themeId, @RequestParam("student") Long studentId) {
    IUserDTO user = (IUserDTO) session.getAttribute("user");
    if (user == null) {
      return LOGIN_REDIRECT;
    }
    Optional<StudentDTO> studentOpt = this.studentService.getById(studentId);
    if (studentOpt.isEmpty()) {
      return "error/404";
    }
    StudentDTO student = studentOpt.get();
    Optional<IThemeDTO> themeOpt = this.themeService.getById(themeId);
    if (themeOpt.isEmpty()) {
      return "error/404";
    }
    IThemeDTO theme = themeOpt.get();
    if (!student.mastersDegree().admin().id().equals(user.id()) || !theme.compatibleMasters().contains(student.mastersDegree())) {
      return "error/401";
    }
    try {
      this.themeService.assignTheme(themeId, studentId);
    } catch (AlreadyExistsException | NotFoundException e) {
      return "error/404";
    }
    return "redirect:/themes/" + themeId;
  }
}
