package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Defence;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.entities.Thesis;
import pt.ul.fc.css.thesisman.business.repositories.DefenceRepository;
import pt.ul.fc.css.thesisman.business.repositories.SubmissionRepository;
import pt.ul.fc.css.thesisman.business.repositories.ThesisRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DefenceCatalog {

  @Autowired private DefenceRepository defenceRepository;
  @Autowired private SubmissionRepository submissionRepository;
  @Autowired private ThesisRepository thesisRepository;

  public Optional<Defence> getById(Long defenceId) {
    return this.defenceRepository.findById(defenceId);
  }

  public List<Defence> getByClassRoomId(Long classRoomId) {
    return this.defenceRepository.findByClassRoom_Id(classRoomId);
  }

  public List<Defence> listParticipatingDefences(Long teacherId) {
    List<Defence> defences = new ArrayList<>();
    defenceRepository.findAll().forEach(d -> {
      if (d.getArguer().getId().equals(teacherId)) {
        defences.add(d);
      } else if (d.getPresident() != null && d.getPresident().getId().equals(teacherId)) {
        defences.add(d);
      } else {
        Optional<Submission> submissionOpt = this.submissionRepository.findSubmissionByDefenceId(d.getId());
        submissionOpt.ifPresent(s -> {
          Optional<Thesis> thesisOpt = this.thesisRepository.findThesisBySubmissionId(s.getId());
          thesisOpt.ifPresent(t -> {
            if (t.getTheme().getAdvisor().getId().equals(teacherId))
              defences.add(d);
          });
        });
      }
    });
    return defences;
  }

}
