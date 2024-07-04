package pt.ul.fc.css.thesisman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pt.ul.fc.css.thesisman.business.entities.*;
import pt.ul.fc.css.thesisman.business.enums.ThemeState;
import pt.ul.fc.css.thesisman.business.repositories.*;

import java.util.Arrays;

@SpringBootApplication
public class ThesisManApp {

  @Autowired private TeacherRepository teacherRepository;
  @Autowired private WorkerRepository workerRepository;
  @Autowired private ClassRoomRepository classRoomRepository;
  @Autowired private MastersRepository mastersRepository;
  @Autowired private StudentRepository studentRepository;
  @Autowired private ThemeRepository themeRepository;
  @Autowired private ThesisRepository thesisRepository;

  public static void main(String[] args) {
    SpringApplication.run(ThesisManApp.class, args);
  }

  @Bean
  public CommandLineRunner fillData() {
    return args -> {
      Teacher teacher1 = new Teacher("amfonseca@fc.ul.pt", "teacher1", "Alcides Fonseca");
      Teacher teacher2 = new Teacher("pjangelo@fc.ul.pt", "teacher2", "Pedro Angelo");
      Teacher teacher3 = new Teacher("dfsoares@fc.ul.pt", "teacher3", "Diogo Soares");
      teacherRepository.saveAll(Arrays.asList(teacher1, teacher2, teacher3));

      MastersDegree masters1 = new MastersDegree(teacher1, "Engenharia Informática");
      MastersDegree masters2 = new MastersDegree(teacher2, "Tecnologias de Informação");
      mastersRepository.saveAll(Arrays.asList(masters1, masters2));

      Worker worker1 = new Worker("sundarpichai@google.com", "worker1", "Sundar Pichai", "Google");
      workerRepository.save(worker1);

      Student student1 = new Student("fc58180@alunos.fc.ul.pt", "student1", "Rodrigo Correia", masters1);
      Student student2 = new Student("fc58188@alunos.fc.ul.pt", "student2", "Laura Cunha", masters1);
      Student student3 = new Student("fc58640@alunos.fc.ul.pt", "student3", "Guilherme Wind", masters1);
      studentRepository.saveAll(Arrays.asList(student1, student2, student3));

      ClassRoom classRoom1 = new ClassRoom("3.2.13");
      ClassRoom classRoom2 = new ClassRoom("3.2.16");
      ClassRoom classRoom3 = new ClassRoom("6.2.53");
      classRoomRepository.saveAll(Arrays.asList(classRoom1, classRoom2, classRoom3));

      Theme theme = new Dissertation("Haskell", teacher1);
      theme.setRemuneration(1000);
      theme.setDescription("Studying functional programming");
      theme.addCompatibleMasters(masters1);
      theme.setState(ThemeState.TAKEN);
      themeRepository.save(theme);

      Theme theme2 = new Project("Google Cloud", teacher1, worker1);
      theme2.setRemuneration(2000);
      theme2.setDescription("Expand google cloud");
      theme2.addCompatibleMasters(masters1);
      themeRepository.save(theme2);

      Thesis thesis = new Thesis(student1, theme);
      thesisRepository.save(thesis);
    };
  }
}
